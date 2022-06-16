using CannedFactoryBusinessLogic.BusinessLogics;
using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.StoragesContracts;
using CannedFactoryDatabaseImplement.Implements;
using System;
using Unity;
using Unity.Lifetime;
using System.Windows.Forms;
using CannedFactoryFileImplement;

namespace CannedFactoryView
{
    static class Program
    {
        private static IUnityContainer container = null;

        public static IUnityContainer Container { get { if (container == null) { container = BuildUnityContainer(); } return container; } }

        /// <summary>
        ///  The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.SetHighDpiMode(HighDpiMode.SystemAware);
            Application.EnableVisualStyles();            
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(Container.Resolve<FormMain>());
            FileDataListSingleton.GetInstance().SaveMeth();
        }

        private static IUnityContainer BuildUnityContainer() {
            var currentContainer = new UnityContainer();
            currentContainer.RegisterType<IComponentStorage, ComponentStorage>(new HierarchicalLifetimeManager());
            currentContainer.RegisterType<IOrderStorage, OrderStorage>(new HierarchicalLifetimeManager());
            currentContainer.RegisterType<ICannedStorage, CannedStorage>(new HierarchicalLifetimeManager());

            currentContainer.RegisterType<IComponentLogic, ComponentLogic>(new HierarchicalLifetimeManager());
            currentContainer.RegisterType<IOrderLogic, OrderLogic>(new HierarchicalLifetimeManager());
            currentContainer.RegisterType<ICannedLogic, CannedLogic>(new HierarchicalLifetimeManager());

            return currentContainer;
        }
    }
}
