using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.StoragesContracts
{
    public interface IOrderStorage
    {
        List<OrderViewModel> GetFullList();

        List<OrderViewModel> GetFilteredList(OrderBindingModel model);

        OrderViewModel GetElement(OrderBindingModel model);

        void Insert(OrderBindingModel model);

        void Update(OrderBindingModel model);

        void Delete(OrderBindingModel model);

    }
}
