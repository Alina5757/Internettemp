using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.BusinessLogicsContracts;
using CannedFactoryContracts.ViewModels;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace CannedFactoryView
{
    public partial class FormCreateOrder : Form
    {
        private readonly ICannedLogic _logicC;
        private readonly IOrderLogic _logicO;
        public FormCreateOrder(ICannedLogic logicC, IOrderLogic logicO)
        {
            InitializeComponent();
            _logicC = logicC;
            _logicO = logicO;

            List<CannedViewModel> list = _logicC.Read(null);
            if (list != null)
            {
                comboBoxCanned.DisplayMember = "CannedName";
                comboBoxCanned.ValueMember = "Id";
                comboBoxCanned.DataSource = list;
                comboBoxCanned.SelectedItem = null;
            }
        }
        
        private void CalcSum() { 
            if(comboBoxCanned.SelectedValue != null && !string.IsNullOrEmpty(textBoxCount.Text))
            {
                try
                {
                    int id = Convert.ToInt32(comboBoxCanned.SelectedValue);
                    CannedViewModel canned = _logicC.Read(new CannedBindingModel { Id = id })?[0];
                    int count = Convert.ToInt32(textBoxCount.Text);
                    textBoxSumm.Text = (count * canned?.Price ?? 0).ToString();
                }
                catch (Exception ex) {
                    MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }

        private void textBoxCount_TextChanged(object sender, EventArgs e)
        {
            CalcSum();
        }

        private void comboBoxCanned_SelectedIndexChanged(object sender, EventArgs e)
        {
            CalcSum();
        }

        private void buttonSave_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBoxCount.Text)) {
                MessageBox.Show("Заполните поле Количество", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (comboBoxCanned.SelectedValue == null)
            {
                MessageBox.Show("Выберите изделие", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            try
            {
                _logicO.CreateOrder(new CreateOrderBindingModel
                {
                    CannedId = Convert.ToInt32(comboBoxCanned.SelectedValue),
                    Count = Convert.ToInt32(textBoxCount.Text),
                    Sum = Convert.ToDecimal(textBoxSumm.Text)
                });
                MessageBox.Show("Сохранение прошло успешно", "Сообщение", MessageBoxButtons.OK, MessageBoxIcon.Information);
                DialogResult = DialogResult.OK;
                Close();
            }
            catch (Exception ex) {
                MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void buttonCansel_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
            Close();
        }
    }
}
