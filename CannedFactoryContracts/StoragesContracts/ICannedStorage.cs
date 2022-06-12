using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.StoragesContracts
{
    public interface ICannedStorage
    {
        List<CannedViewModel> GetFullList();

        List<CannedViewModel> GetFilteredList(CannedBindingModel model);

        CannedViewModel GetElement(CannedBindingModel model);

        void Insert(CannedBindingModel model);

        void Update(CannedBindingModel model);

        void Delete(CannedBindingModel model);
    }
}
