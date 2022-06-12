using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WarehouseFactoryBusinessLogic.BusinessLogics
{
    public class WarehouseLogic : IWarehouseLogic
    {
        private readonly IWarehouseStorage _warehouseStorage;

        public WarehouseLogic(IWarehouseStorage warehouseStorage)
        {
            _warehouseStorage = warehouseStorage;
        }

        public List<WarehouseViewModel> Read(WarehouseBindingModel model)
        {
            if (model == null)
            {
                return _warehouseStorage.GetFullList();
            }

            if (model.Id.HasValue)
            {
                return new List<WarehouseViewModel> { _warehouseStorage.GetElement(model) };
            }

            return _warehouseStorage.GetFilteredList(model);
        }

        public void CreateOrUpdate(WarehouseBindingModel model)
        {
            var element = _warehouseStorage.GetElement(new WarehouseBindingModel
            {
                Name = model.Name
            });
            if (element != null && element.Id != model.Id)
            {
                throw new Exception("Уже есть склад с таким названием");
            }
            if (model.Id.HasValue)
            {
                _warehouseStorage.Update(model);
            }
            else
            {
                _warehouseStorage.Insert(model);
            }
        }

        public void Delete(WarehouseBindingModel model)
        {
            var element = _warehouseStorage.GetElement(new WarehouseBindingModel
            {
                Id = model.Id
            });

            if (element == null)
            {
                throw new Exception("Элемент не найден");
            }

            _warehouseStorage.Delete(model);
        }

        public void FillWarehouse(FillingWarehouse model)
        {
            var element = _warehouseStorage.GetElement(new WarehouseBindingModel
            {
                Id = model.WarehouseId
            });

            if (element == null)
            {
                throw new Exception("Склад не найден");
            }
            Dictionary<int, int> tempdict = new Dictionary<int, int>();
            bool added = false;
            foreach(var component in element.StoredComponents)
            {
                if (component.Key == model.ComponentId)
                {
                    tempdict.Add(component.Key, component.Value.Item2 + model.Count);
                    added = true;
                }
                else 
                {
                    tempdict.Add(component.Key, component.Value.Item2);
                }
            }
            if (!added) 
            {
                tempdict.Add((int)model.ComponentId, model.Count);
            }
            WarehouseBindingModel warehouse = new WarehouseBindingModel() { 
                Id = element.Id,
                FIOChief = element.FIOChief,
                DateCreate = element.DateCreate,
                Name = element.Name,
                StoredComponents = tempdict
            };

            _warehouseStorage.Update(warehouse);
        }
    }
}