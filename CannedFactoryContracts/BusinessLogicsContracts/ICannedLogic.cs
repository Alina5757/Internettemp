using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.BusinessLogicsContracts
{
    public interface ICannedLogic
    {
        List<CannedViewModel> Read(CannedBindingModel model);

        void CreateOrUpdate(CannedBindingModel model);

        void Delete(CannedBindingModel model);
    }
}
