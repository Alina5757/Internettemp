using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using CannedFactoryListImplement.Models;
using System;
using System.Collections.Generic;
using System.Linq;

namespace CannedFactoryListImplement.Implements
{
    public class CannedStorage : ICannedStorage
    {
        private readonly DataListSingleton source;

        public CannedStorage() {
            source = DataListSingleton.GetInstance();
        }

        public List<CannedViewModel> GetFullList() 
        {
            var result = new List<CannedViewModel>();
            foreach (var component in source.Canneds)
            {
                result.Add(CreateModel(component));
            }
            return result;
        }

        public List<CannedViewModel> GetFilteredList(CannedBindingModel model) 
        {
            if (model == null) 
            {
                return null;
            }

            var result = new List<CannedViewModel>();
            foreach (var canned in source.Canneds) 
            {
                if (canned.CannedName.Contains(model.CannedName)) 
                {
                    result.Add(CreateModel(canned));
                }
            }
            return result;
        }

        public CannedViewModel GetElement(CannedBindingModel model) 
        {
            if (model == null) 
            {
                return null;
            }
            
            foreach (var canned in source.Canneds) 
            {
                if (canned.Id == model.Id || canned.CannedName == model.CannedName) 
                {
                    return CreateModel(canned);
                }
            }
            return null;
        }

        public void Insert(CannedBindingModel model) 
        {
            var tempCanned = new Canned { Id = 1, CannedComponents = new Dictionary<int, int>() };
            foreach (var canned in source.Canneds) 
            {
                if (canned.Id >= tempCanned.Id) 
                {
                    tempCanned.Id = canned.Id + 1;
                }
            }

            source.Canneds.Add(CreateModel(model, tempCanned));
        }

        public void Update(CannedBindingModel model) {
            Canned tempCanned = null;
            foreach (var canned in source.Canneds) {
                if (canned.Id == model.Id) {
                    tempCanned = canned;
                }
            }
            if (tempCanned == null) {
                throw new Exception("Элемент не найден");
            }

            CreateModel(model, tempCanned);
        }

        public void Delete(CannedBindingModel model)
        {
            for (int i = 0; i < source.Canneds.Count; i++) {
                if (source.Canneds[i].Id == model.Id) {
                    source.Canneds.RemoveAt(i);
                    return;
                }
            }
            throw new Exception("Элемент не найден");
        }

        private static Canned CreateModel(CannedBindingModel model, Canned canned) {
            canned.CannedName = model.CannedName;
            canned.Price = model.Price;

            //удаляем убранные
            foreach (var key in canned.CannedComponents.Keys.ToList()) {
                if (!model.CannedComponents.ContainsKey(key)) {
                    canned.CannedComponents.Remove(key);
                }
            }

            //обновляем существующие и добавляем новые
            foreach (var component in model.CannedComponents) {
                if (canned.CannedComponents.ContainsKey(component.Key))
                {
                    canned.CannedComponents[component.Key] = 
                        model.CannedComponents[component.Key].Item2;
                }
                else {
                    canned.CannedComponents.Add(component.Key, 
                        model.CannedComponents[component.Key].Item2);
                }
            }
            return canned;
        }

        private CannedViewModel CreateModel(Canned canned) {
            //требуется дополнительно получить список компонентов для изделия с названиями и их количество
            var cannedComponents = new Dictionary<int, (string, int)>();
            foreach (var cc in canned.CannedComponents) {
                string componentName = string.Empty;
                foreach (var component in source.Components) {
                    if (cc.Key == component.Id) {
                        componentName = component.ComponentName;
                        break;
                    }
                }
                cannedComponents.Add(cc.Key, (componentName, cc.Value));
            }
            return new CannedViewModel
            {
                Id = canned.Id,
                CannedName = canned.CannedName,
                Price = canned.Price,
                CannedComponents = cannedComponents
            };
        }

    }
}
