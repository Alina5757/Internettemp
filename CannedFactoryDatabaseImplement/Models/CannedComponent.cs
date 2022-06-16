using System.ComponentModel.DataAnnotations;

namespace CannedFactoryDatabaseImplement.Models
{
    /// <summary>
    /// Сколько компонентов, требуется при изготовлении изделия
    /// </summary>
    public class CannedComponent
    {
        public int Id { get; set; }
        public int CannedId { get; set; }
        public int ComponentId { get; set; }

        [Required]
        public int Count { get; set; }
        public virtual Component Component { get; set; }
        public virtual Canned Canned { get; set; }
    }
}
