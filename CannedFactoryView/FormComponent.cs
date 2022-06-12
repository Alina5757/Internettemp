using CannedFactoryContracts.BindingModels;
using CannedFactoryContracts.BusinessLogicsContracts;
using System;
using System.Windows.Forms;

namespace CannedFactoryView
{
    public partial class FormComponent : Form
    {
        public int Id { set { id = value; } }
        private readonly IComponentLogic _logic;
        private int? id;
        public FormComponent(IComponentLogic logic)
        {
            InitializeComponent();
            _logic = logic;
        }

        private void FormComponent_Load(object sender, EventArgs e) {
            if (id.HasValue)
            {
                try
                {
                    var view = _logic.Read(new ComponentBindingModel { Id = id })?[0];
                    if (view != null)
                    {
                        textBox.Text = view.ComponentName;
                    }
                }
                catch (Exception ex) {
                    MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                }
            }
        }
        private void buttonSave_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrEmpty(textBox.Text)) {
                MessageBox.Show("Заполните название", "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            try {
                _logic.CreateOrUpdate(new ComponentBindingModel {
                    Id = id,
                    ComponentName = textBox.Text
                });
                MessageBox.Show("Создание прошло успешно", "Сообщение", MessageBoxButtons.OK, MessageBoxIcon.Information);
                DialogResult = DialogResult.OK;
                Close();
            }
            catch (Exception ex) {
                MessageBox.Show(ex.Message, "Ошибка", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void buttonClose_Click(object sender, EventArgs e)
        {
            DialogResult = DialogResult.Cancel;
            Close();
        }

        
    }
}
