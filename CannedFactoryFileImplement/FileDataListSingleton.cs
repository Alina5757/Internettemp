using CannedFactoryContracts.Enums;
using CannedFactoryFileImplement.Models;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading;
using System.Xml.Linq;

namespace CannedFactoryFileImplement
{
    public class FileDataListSingleton
    {
        private static FileDataListSingleton instance;

        private readonly string ComponentFileName = "Component.xml";

        private readonly string OrderFileName = "Order.xml";

        private readonly string CannedFileName = "Canned.xml";

        public List<Component> Components { get; set; }

        public List<Order> Orders { get; set; }

        public List<Canned> Canneds { get; set; }

        private FileDataListSingleton() {
            Components = LoadComponents();
            Orders = LoadOrders();
            Canneds = LoadCanneds();
        }

        public static FileDataListSingleton GetInstance() {
            if (instance == null)
            {
                instance = new FileDataListSingleton();
            }
            return instance;
        }

        public void SaveMeth() {
            SaveComponents();
            SaveOrders();
            SaveCanneds();
        }
        
        private List<Component> LoadComponents() {
            var list = new List<Component>();

            if (File.Exists(ComponentFileName)) {
                var xDocument = XDocument.Load(ComponentFileName);
                var xElements = xDocument.Root.Elements("Component").ToList();
                foreach (var elem in xElements) {
                    list.Add(new Component
                    {
                        Id = Convert.ToInt32(elem.Attribute("Id").Value),
                        ComponentName = elem.Element("ComponentName").Value
                    });
                }
            }

            return list;
        }

        private List<Order> LoadOrders() {
            var list = new List<Order>();
            if (File.Exists(OrderFileName)) {
                var xDocument = XDocument.Load(OrderFileName);
                var xElements = xDocument.Root.Elements("Order").ToList();
                foreach (var elem in xElements) {                    
                    list.Add(new Order
                    {
                        Id = Convert.ToInt32(elem.Attribute("Id").Value),
                        CannedId = Convert.ToInt32(elem.Element("CannedId").Value),
                        Count = Convert.ToInt32(elem.Element("Count").Value),
                        Sum = Convert.ToInt32(elem.Element("Sum").Value),
                        status = (OrderStatus)Enum.Parse(typeof(OrderStatus), (elem.Element("Status").Value)),
                        DateCreate = DateTime.Parse(elem.Element("DateCreate").Value),
                        DateImplement = elem.Element("DateImplement").Value == null ? DateTime.MinValue : DateTime.Parse(elem.Element("DateImplement").Value)
                    });
                }
            }
            return list;
        }

        private List<Canned> LoadCanneds() { 
            var list = new List<Canned>();
            if (File.Exists(CannedFileName)) { 
                var xDocument = XDocument.Load(CannedFileName);
                var xElements = xDocument.Root.Elements("Canned").ToList();

                foreach (var elem in xElements) {
                    var cannComp = new Dictionary<int, int>();
                    foreach (var component in
                        elem.Element("CannedComponents").Elements("CannedComponent").ToList()) {
                        cannComp.Add(Convert.ToInt32(component.Element("Key").Value),
                            Convert.ToInt32(component.Element("Value").Value));
                    }
                    list.Add(new Canned {
                        Id = Convert.ToInt32(elem.Attribute("Id").Value),
                        CannedName = elem.Element("CannedName").Value,
                        Price = Convert.ToDecimal(elem.Element("Price").Value),
                        CannedComponents = cannComp
                    });
                }
            }
            return list;
        }

        private void SaveComponents() {
            if (Components != null) {
                var xElement = new XElement("Components");

                foreach (var component in Components) {
                    xElement.Add(new XElement("Component",
                        new XAttribute("Id", component.Id),
                        new XElement("ComponentName", component.ComponentName)));
                }

                var xDocument = new XDocument(xElement);
                xDocument.Save(ComponentFileName);
            }
        }

        private void SaveOrders() {
            if (Orders != null)
            {
                var xElement = new XElement("Orders");

                foreach (var order in Orders)
                {
                    DateTime dateIm = (DateTime)(order.DateImplement == null ? DateTime.MinValue : order.DateImplement);

                    xElement.Add(new XElement("Order",
                        new XAttribute("Id", order.Id),
                        new XElement("CannedId", order.CannedId),
                        new XElement("Count", order.Count),
                        new XElement("Sum", order.Sum),
                        new XElement("Status", order.status),
                        new XElement("DateCreate", order.DateCreate),
                        new XElement("DateImplement", dateIm)));
                }

                var xDocument = new XDocument(xElement);
                xDocument.Save(OrderFileName);
            }
        }

        private void SaveCanneds() {
            if (Canneds != null) {
                var xElement = new XElement("Canneds");

                foreach (var canned in Canneds) {
                    var compElement = new XElement("CannedComponents");
                    foreach (var component in canned.CannedComponents) {
                        compElement.Add(new XElement("CannedComponent",
                            new XElement("Key", component.Key),
                            new XElement("Value", component.Value)));
                    }
                    xElement.Add(new XElement("Canned",
                        new XAttribute("Id", canned.Id),
                        new XElement("CannedName", canned.CannedName),
                        new XElement("Price", canned.Price),
                        compElement));
                }

                var xDocument = new XDocument(xElement);
                xDocument.Save(CannedFileName);
            }
        }        
    }
}
