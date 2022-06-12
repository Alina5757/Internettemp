﻿using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryContracts.BindingModels
{
    //Изготавливаемое изделие
    public class CannedBindingModel
    {
        public int? Id { get; set; }
        public string CannedName { get; set; }
        public decimal Price { get; set; }
        public Dictionary<int, (string, int)> CannedComponents { get; set; }
    }
}
