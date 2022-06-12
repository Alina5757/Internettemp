using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.BusinessLogicsContracts
{
    public interface IComponentLogic
    {
        List<ComponentViewModel> Read(ComponentBindingModel model);

        void CreateOrUpdate(ComponentBindingModel model);

        void Delete(ComponentBindingModel model);
    }
}
