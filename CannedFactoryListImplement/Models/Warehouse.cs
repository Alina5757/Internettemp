using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CannedFactoryListImplement.Models
{
    public class Warehouse
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string FIOChief { get; set; }

        public DateTime DateCreate { get; set; }

        public Dictionary<int, int> StoredComponents { get; set; }
    }
}
