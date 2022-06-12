using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.Enums;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryBusinessLogic.BusinessLogics
{
    public class OrderLogic : IOrderLogic
    {
        private readonly IOrderStorage _orderStorage;

        public OrderLogic(IOrderStorage orderStorage)
        {
            _orderStorage = orderStorage;
        }

        public List<OrderViewModel> Read(OrderBindingModel model)
        {
            if (model == null)
            {
                return _orderStorage.GetFullList();
            }
            if (model.Id.HasValue)
            {
                return new List<OrderViewModel> { _orderStorage.GetElement(model) };
            }
            return _orderStorage.GetFilteredList(model);
        }

        public void CreateOrder(CreateOrderBindingModel model)
        {
            var element = _orderStorage.GetElement(new OrderBindingModel
            {
                CannedId = model.CannedId,
                Count = model.Count,
                Sum = model.Sum,
                Status = CannedFactoryContracts.Enums.OrderStatus.Принят,
                DateCreate = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, DateTime.Now.Hour, DateTime.Now.Minute, DateTime.Now.Second)
            });
            if (element != null)
            {
                throw new Exception("Уже есть компонент с таким ID");
            }
            _orderStorage.Insert(new OrderBindingModel {
                CannedId = model.CannedId,
                Count = model.Count,
                Sum = model.Sum,
                Status = CannedFactoryContracts.Enums.OrderStatus.Принят,
                DateCreate = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, DateTime.Now.Hour, DateTime.Now.Minute, DateTime.Now.Second)
            });
        }

        public void TakeOrderInWork(ChangeStatusBindingModel model)
        {
            var element = _orderStorage.GetElement(new OrderBindingModel
            {
                Id = model.OrderId               
            });
            if (element == null)
            {
                throw new Exception("Консервы не найдены");
            }
            if (element.Status != OrderStatus.Принят.ToString()) {
                throw new Exception("Заказ не в статусе 'Принят'");
            }
            if (element.Status == OrderStatus.Принят.ToString()) {
                _orderStorage.Update(new OrderBindingModel
                {
                    Id = model.OrderId,
                    CannedId = element.CannedId,
                    Count = element.Count,
                    Sum = element.Sum,
                    Status = OrderStatus.Выполняется,
                    DateCreate = element.DateCreate
                });
            }
        }

        public void FinishOrder(ChangeStatusBindingModel model)
        {
            var element = _orderStorage.GetElement(new OrderBindingModel
            {
                Id = model.OrderId
            });
            if (element == null)
            {
                throw new Exception("Консервы не найдены");
            }
            if (element.Status != OrderStatus.Выполняется.ToString())
            {
                throw new Exception("Заказ не в статусе 'Выполняется'");
            }
            if (element.Status == OrderStatus.Выполняется.ToString())
            {
                _orderStorage.Update(new OrderBindingModel
                {
                    Id = model.OrderId,
                    CannedId = element.CannedId,
                    Count = element.Count,
                    Sum = element.Sum,
                    Status = OrderStatus.Готов,
                    DateCreate = element.DateCreate
                });
            }
        }

        public void DeliveryOrder(ChangeStatusBindingModel model)
        {
            var element = _orderStorage.GetElement(new OrderBindingModel
            {
                Id = model.OrderId
            });
            if (element == null)
            {
                throw new Exception("Консервы не найдены");
            }
            if (element.Status != OrderStatus.Готов.ToString())
            {
                throw new Exception("Заказ не в статусе 'Готов'");
            }
            if (element.Status == OrderStatus.Готов.ToString())
            {
                _orderStorage.Update(new OrderBindingModel
                {
                    Id = model.OrderId,
                    CannedId = element.CannedId,
                    Count = element.Count,
                    Sum = element.Sum,
                    Status = OrderStatus.Выдан,
                    DateCreate = element.DateCreate,
                    DateImplement = new DateTime(DateTime.Now.Year, DateTime.Now.Month, DateTime.Now.Day, DateTime.Now.Hour, DateTime.Now.Minute, DateTime.Now.Second)
                });
            }
        }
    }
}
