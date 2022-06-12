using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using CannedFactoryListImplement.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryListImplement.Implements
{
    public class WarehouseStorage : IWarehouseStorage
    {
        private readonly DataListSingleton source;
        public WarehouseStorage()
        {
            source = DataListSingleton.GetInstance();
        }

        public List<WarehouseViewModel> GetFullList()
        {
            var result = new List<WarehouseViewModel>();
            foreach (var component in source.Warehouses)
            {
                result.Add(CreateModel(component));
            }
            return result;
        }

        public List<WarehouseViewModel> GetFilteredList(WarehouseBindingModel model)
        {
            if (model == null)
            {
                return null;
            }

            var result = new List<WarehouseViewModel>();
            foreach (var warehouse in source.Warehouses)
            {
                if (warehouse.Name.Contains(model.Name))
                {
                    result.Add(CreateModel(warehouse));
                }
            }
            return result;
        }

        public WarehouseViewModel GetElement(WarehouseBindingModel model)
        {
            if (model == null)
            {
                return null;
            }
            foreach (var warehouse in source.Warehouses)
            {
                if (warehouse.Id == model.Id || warehouse.Name == model.Name)
                {
                    return CreateModel(warehouse);
                }
            }
            return null;
        }

        public void Insert(WarehouseBindingModel model)
        {
            var tempWarehouse = new Warehouse
            {
                Id = 1,
                StoredComponents = new Dictionary<int, int>()
            };
            foreach (var warehouse in source.Warehouses)
            {
                if (warehouse.Id >= tempWarehouse.Id)
                {
                    tempWarehouse.Id = warehouse.Id + 1;
                }
            }

            source.Warehouses.Add(CreateModel(model, tempWarehouse));
        }

        public void Update(WarehouseBindingModel model)
        {
            Warehouse tempWarehouse = null;
            foreach (var warehouse in source.Warehouses)
            {
                if (warehouse.Id == model.Id)
                {
                    tempWarehouse = warehouse;
                }
            }
            if (tempWarehouse == null)
            {
                throw new Exception("Элемент не найден");
            }

            CreateModel(model, tempWarehouse);
        }

        public void Delete(WarehouseBindingModel model)
        {
            for (int i = 0; i < source.Warehouses.Count; i++)
            {
                if (source.Warehouses[i].Id == model.Id)
                {
                    source.Warehouses.RemoveAt(i);
                    return;
                }
            }
            throw new Exception("Элемент не найден");
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
