﻿using System;
using System.Collections.Generic;
using System.Text;

namespace CannedFactoryListImplement.Models
{
    //компонент, необходимый для изготовления изделия
    public class Component
    {
        public int Id { get; set; }

        public string ComponentName { get; set; }
    }
}