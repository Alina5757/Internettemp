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
    public partial class FormWarehouse : Form
    {
        public int Id { set { id = value; } }
        private readonly IWarehouseLogic _logic;
        private int? id;
        private Dictionary<string, int> Components;
        private Dictionary<int, int> IntIntComponents;

        public FormWarehouse(IWarehouseLogic logic)
        {
            InitializeComponent();
            _logic = logic;
        }

        private void FormWarehouse_Load(object sender, EventArgs e)
        {
            if (id.HasValue)
            {
                try
                {
                    WarehouseViewModel view = _logic.Read(new WarehouseBindingModel { Id = id.Value })?[0];
                    if (view != null)
                    {
                        textBoxName.Text = view.Name;
                        textBoxFIOChief.Text = view.FIOChief;
                        dateTimePicker1.Value = view.DateCreate;
                        if (Components == null)
                        {
                            Components = new Dictionary<string, int>();
                            IntIntComponents = new Dictionary<int, int>();
                        }
                        foreach (var component in view.StoredComponents) {
                            Components.Add(component.Value.Item1, component.Value.Item2);
                            IntIntComponents.Add(component.Key, component.Value.Item2);
                        }
                    }
                    if (Components != null)
                    {
                        dataGridView1.Rows.Clear();
                        foreach (var cc in Components)
                        {
                            dataGridView1.Rows.Add(new object[] {  cc.Key, cc.Value });
                        }
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBoxName.Text))
            {
                MessageBox.Show("Заполните название", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (string.IsNullOrEmpty(textBoxFIOChief.Text))
            {
                MessageBox.Show("Заполните ФИО ответсвенного", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            try
            {
                if (id == null) {
                    Components = new Dictionary<string, int>();
                    IntIntComponents = new Dictionary<int, int>();
                }

                _logic.CreateOrUpdate(new WarehouseBindingModel
                {
                    Id = id,
                    Name = textBoxName.Text,
                    FIOChief = textBoxFIOChief.Text,
                    DateCreate = dateTimePicker1.Value,
                    StoredComponents = IntIntComponents
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
    }
}
