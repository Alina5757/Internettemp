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
    public class OrderStorage : IOrderStorage
    {
        private readonly FileDataListSingleton source;

        public OrderStorage() { 
            source = FileDataListSingleton.GetInstance();
        }

        public List<OrderViewModel> GetFullList()
        {
            return source.Orders
                .Select(CreateModel)
                .ToList();
        }

        public List<OrderViewModel> GetFilteredList(OrderBindingModel model)
        {
            if (model == null)
            {
                return null;
            }

            return source.Orders
            .Where(rec => rec.Id.Equals(model.Id))
            .Select(CreateModel)
            .ToList();            
        }

        public OrderViewModel GetElement(OrderBindingModel model)
        {
            if (model == null)
            {
                return null;
            }

            var order = source.Orders
                .FirstOrDefault(rec => rec.Id == model.Id);

            return order != null ? CreateModel(order) : null;            
        }

        public void Insert(OrderBindingModel model)
        {
            int maxId = source.Orders.Count > 0 ? source.Orders.Max(rec => rec.Id) : 0;
            var element = new Order { Id = maxId + 1 };
            source.Orders.Add(CreateModel(model, element));
        }

        public void Update(OrderBindingModel model)
        {
            var element = source.Orders.FirstOrDefault(rec => rec.Id == model.Id);

            if (element == null)
            {
                throw new Exception("Элемент не найден");
            }

            CreateModel(model, element);           
        }

        public void Delete(OrderBindingModel model)
        {
            Component element = source.Components.FirstOrDefault(rec => rec.Id == model.Id);
            if (element != null)
            {
                source.Components.Remove(element);
            }
            else
            {
                throw new Exception("Элемент не найден");
            }
        }

        private static Order CreateModel(OrderBindingModel model, Order order)
        {
            order.CannedId = model.CannedId;
            order.Count = model.Count;
            order.DateCreate = model.DateCreate;
            order.DateImplement = model.DateImplement;
            order.Sum = model.Sum;
            order.status = model.Status;
            return order;
        }

        private static OrderViewModel CreateModel(Order order)
        {
            string nameCanned = null;
            foreach (Canned canned in FileDataListSingleton.GetInstance().Canneds)
            {
                if (canned.Id == order.CannedId)
                {
                    nameCanned = canned.CannedName;
                    break;
                }
            }

            return new OrderViewModel
            {
                Id = order.Id,
                CannedId = order.CannedId,
                CannedName = nameCanned,
                Count = order.Count,
                Sum = order.Sum,
                Status = order.status.ToString(),
                DateCreate = order.DateCreate,
                DateImplement = order.DateImplement
            };
        }
    }
}
