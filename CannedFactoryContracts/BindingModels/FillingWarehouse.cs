using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryContracts.BindingModels
{
    public class FillingWarehouse
    {
        public int? WarehouseId { get; set; }
        public int? ComponentId { get; set; }
        public int Count { get; set; }
    }
}
