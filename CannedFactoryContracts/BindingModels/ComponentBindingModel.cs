using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.BindingModels
{
    //Компонент, необходимый для изготовления изделия
    public class ComponentBindingModel
    {
        public int? Id { get; set; }
        public string ComponentName { get; set; }
    }
}
