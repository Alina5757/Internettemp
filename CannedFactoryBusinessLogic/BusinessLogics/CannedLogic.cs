using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using CannedFactoryContracts.BindingModels;
using System;
using System.Collections.Generic;

namespace CannedFactoryBusinessLogic.BusinessLogics
{
    public class CannedLogic: ICannedLogic
    {
        private readonly ICannedStorage _cannedStorage;

        public CannedLogic(ICannedStorage cannedStorage)
        {
            _cannedStorage = cannedStorage;
        }

        public List<CannedViewModel> Read(CannedBindingModel model)
        {
            if (model == null)
            {
                return _cannedStorage.GetFullList();
            }

            if (model.Id.HasValue)
            {
                return new List<CannedViewModel> { _cannedStorage.GetElement(model) };
            }

            return _cannedStorage.GetFilteredList(model);
        }

        public void CreateOrUpdate(CannedBindingModel model)
        {
            var element = _cannedStorage.GetElement(new CannedBindingModel
            {
                CannedName = model.CannedName,
                CannedComponents = model.CannedComponents
            });
            if (element != null && element.Id != model.Id)
            {
                throw new Exception("Уже есть консервы с таким названием");
            }
            if (model.Id.HasValue)
            {
                _cannedStorage.Update(model);
            }
            else
            {
                _cannedStorage.Insert(model);
            }
        }

        public void Delete(CannedBindingModel model)
        {
            var element = _cannedStorage.GetElement(new CannedBindingModel
            {
                Id = model.Id
            });

            if (element == null)
            {
                throw new Exception("Элемент не найден");
            }

            _cannedStorage.Delete(model);
        }        
    }
}
