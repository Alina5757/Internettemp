using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryFileImplement.Models
{
    //компонент, необходимый для изготовления изделия
    public class Component
    {
        public int Id { get; set; }

        public string ComponentName { get; set; }
    }
}
