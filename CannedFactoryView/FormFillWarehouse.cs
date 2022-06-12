using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace CannedFactoryView
{
    public partial class FormFillWarehouse : Form
    {
        private readonly IWarehouseLogic _warehouselogic;
        private readonly IComponentLogic _componentlogic;
        public FormFillWarehouse(IWarehouseLogic warehouselogic, IComponentLogic componentLogic)
        {
            _warehouselogic = warehouselogic;
            _componentlogic = componentLogic;
            InitializeComponent();
        }

        private void buttonFill_Click(object sender, EventArgs e)
        {
            if (comboBoxComponent.SelectedItem == null)
            {
                MessageBox.Show("Выберите компонент", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (comboBoxWarehouse.SelectedItem == null)
            {
                MessageBox.Show("Выберите склад", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (string.IsNullOrEmpty(textBoxKolvo.Text))
            {
                MessageBox.Show("Выберите количество", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            try
            {                
                _warehouselogic.FillWarehouse(new FillingWarehouse
                {
                    WarehouseId = ((WarehouseViewModel)comboBoxWarehouse.SelectedItem).Id,
                    ComponentId = ((ComponentViewModel)comboBoxComponent.SelectedItem).Id,
                    Count = Convert.ToInt32(textBoxKolvo.Text)
                });
                MessageBox.Show("Создание прошло успешно", "Сообщение", MessageBoxButtons.OK, MessageBoxIcon.Information);
                DialogResult = DialogResult.OK;
                Close();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void buttonCancel_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
            Close();
        }

        private void FormFillWarehouse_Load(object sender, EventArgs e)
        {
            List<WarehouseViewModel> spisokWarehouses = _warehouselogic.Read(null);
            if (spisokWarehouses != null)
            {
                comboBoxWarehouse.DisplayMember = "Name";
                comboBoxWarehouse.ValueMember = "Id";
                comboBoxWarehouse.DataSource = spisokWarehouses;
                comboBoxWarehouse.SelectedItem = null;
            }
            List<ComponentViewModel> spisokComponents = _componentlogic.Read(null);
            if (spisokComponents != null)
            {
                comboBoxComponent.DisplayMember = "ComponentName";
                comboBoxComponent.ValueMember = "Id";
                comboBoxComponent.DataSource = spisokComponents;
                comboBoxComponent.SelectedItem = null;
            }
        }
    }
}