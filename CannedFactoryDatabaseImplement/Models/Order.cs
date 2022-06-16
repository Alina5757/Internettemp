using CannedFactoryContracts.Enums;
using System;
using System.ComponentModel.DataAnnotations;

namespace CannedFactoryDatabaseImplement.Models
{
    public class Order
    {
        public int Id { get; set; }
        public int CannedId { get; set; }

        [Required]
        public string CannedName { get; set; }

        [Required]
        public int Count { get; set; }

        [Required]
        public decimal Sum { get; set; }

        [Required]
        public OrderStatus Status { get; set; }

        [Required]
        public DateTime DateCreate { get; set; }

        public DateTime DateImplement { get; set; }

        public virtual Canned Canned { get; set; }
    }
}
