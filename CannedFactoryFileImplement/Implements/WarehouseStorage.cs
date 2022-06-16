using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using CannedFactoryFileImplement.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryFileImplement.Implements
{
    public class WarehouseStorage : IWarehouseStorage
    {
        private readonly FileDataListSingleton source;
        public WarehouseStorage()
        {
            source = FileDataListSingleton.GetInstance();
        }

        public List<WarehouseViewModel> GetFullList()
        {
            return source.Warehouses
                .Select(CreateModel)
                .ToList();            
        }

        public List<WarehouseViewModel> GetFilteredList(WarehouseBindingModel model)
        {
            if (model == null)
            {
                return null;
            }

            return source.Warehouses
                .Where(rec => rec.Name.Equals(model.Name))
                .Select(CreateModel)
                .ToList();            
        }

        public WarehouseViewModel GetElement(WarehouseBindingModel model)
        {
            if (model == null)
            {
                return null;
            }
            var warehouse = source.Warehouses
                .FirstOrDefault(rec => rec.Id == model.Id);
            return warehouse != null ? CreateModel(warehouse) : null;
        }

        public void Insert(WarehouseBindingModel model)
        {
            int maxId = source.Warehouses.Count > 0 ? source.Warehouses.Max(rec => rec.Id) : 0;
            var element = new Warehouse
            {
                Id = maxId + 1,
                StoredComponents = new Dictionary<int, int>()
            };
            source.Warehouses.Add(CreateModel(model, element));
        }

        public void Update(WarehouseBindingModel model)
        {
            var warehouse = source.Warehouses.FirstOrDefault(rec => rec.Id == model.Id);
            if (warehouse == null)
            {
                throw new Exception("Элемент не найден");
            }

            CreateModel(model, warehouse);
        }

        public void Delete(WarehouseBindingModel model)
        {
            var warehouse = source.Warehouses.FirstOrDefault(rec => rec.Id == model.Id);
            if (warehouse != null)
            {
                source.Warehouses.Remove(warehouse);
            }
            else
            {
                throw new Exception("Элемент не найден");
            }
        }

        public bool TakeComponents(Dictionary<int, (string, int)> components, int count)
        {            
            foreach (var component in components) {
                int countStoredComponents = source.Warehouses
                    .Where(warehouse => warehouse.StoredComponents.ContainsKey(component.Key))
                    .Sum(elem => elem.StoredComponents[component.Key]);                
                if (countStoredComponents < component.Value.Item2 * count) {
                    return false;
                }
            }
            foreach (var component in components) {
                int needComponents = component.Value.Item2 * count;
                var warehouses = source.Warehouses
                    .Where(rec => rec.StoredComponents.ContainsKey(component.Key))
                    .OrderByDescending(rec => rec.StoredComponents[component.Key]);

                foreach (var warehouse in warehouses)
                {
                    if (warehouse.StoredComponents[component.Key] > needComponents)
                    {
                        warehouse.StoredComponents[component.Key] -= needComponents;
                        needComponents = 0;
                        break;
                    }
                    else 
                    {
                        needComponents -= warehouse.StoredComponents[component.Key];
                        warehouse.StoredComponents.Remove(component.Key);
                    }
                }
            }
            return true;
        }

        private static Warehouse CreateModel(WarehouseBindingModel model, Warehouse warehouse)
        {
            warehouse.Name = model.Name;
            warehouse.FIOChief = model.FIOChief;
            warehouse.DateCreate = model.DateCreate;
            warehouse.StoredComponents = model.StoredComponents;

            return warehouse;
        }

        private WarehouseViewModel CreateModel(Warehouse warehouse)
        {
            //требуется дополнительно получить список компонентов для изделия с названиями и их количество
            var storedComponents = new Dictionary<int, (string, int)>();
            foreach (var sc in warehouse.StoredComponents)
            {
                string componentName = string.Empty;
                foreach (var component in source.Components)
                {
                    if (sc.Key == component.Id)
                    {
                        componentName = component.ComponentName;
                        break;
                    }
                }
                storedComponents.Add(sc.Key, (componentName, sc.Value));
            }
            return new WarehouseViewModel
            {
                Id = warehouse.Id,
                Name = warehouse.Name,
                FIOChief = warehouse.FIOChief,
                DateCreate = warehouse.DateCreate,
                StoredComponents = storedComponents
            };
        }
    }
}
