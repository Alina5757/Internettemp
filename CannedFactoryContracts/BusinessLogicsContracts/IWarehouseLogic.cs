using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryContracts.BusinessLogicsContracts
{
    public interface IWarehouseLogic
    {
        List<WarehouseViewModel> Read(WarehouseBindingModel model);

        void CreateOrUpdate(WarehouseBindingModel model);

        void Delete(WarehouseBindingModel model);

        void FillWarehouse(FillingWarehouse model);
    }
}
