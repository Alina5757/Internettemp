
namespace CannedFactoryView
{
    partial class FormMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.buttonCreateOrder = new System.Windows.Forms.Button();
            this.buttonTakeOrderInWork = new System.Windows.Forms.Button();
            this.buttonOrderReady = new System.Windows.Forms.Button();
            this.buttonIssuedOrder = new System.Windows.Forms.Button();
            this.buttonRefact = new System.Windows.Forms.Button();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuItemComponents = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuItemCanneds = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuItemWarehouses = new System.Windows.Forms.ToolStripMenuItem();
            this.ToolStripMenuItemFillWarhouse = new System.Windows.Forms.ToolStripMenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).BeginInit();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.BackgroundColor = System.Drawing.SystemColors.ControlLightLight;
            this.dataGridView1.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.DisableResizing;
            this.dataGridView1.Location = new System.Drawing.Point(12, 41);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.ReadOnly = true;
            this.dataGridView1.RowHeadersWidthSizeMode = System.Windows.Forms.DataGridViewRowHeadersWidthSizeMode.DisableResizing;
            this.dataGridView1.RowTemplate.Height = 25;
            this.dataGridView1.Size = new System.Drawing.Size(816, 346);
            this.dataGridView1.TabIndex = 0;
            // 
            // buttonCreateOrder
            // 
            this.buttonCreateOrder.Location = new System.Drawing.Point(861, 53);
            this.buttonCreateOrder.Name = "buttonCreateOrder";
            this.buttonCreateOrder.Size = new System.Drawing.Size(150, 31);
            this.buttonCreateOrder.TabIndex = 1;
            this.buttonCreateOrder.Text = "Создать заказ";
            this.buttonCreateOrder.UseVisualStyleBackColor = true;
            this.buttonCreateOrder.Click += new System.EventHandler(this.buttonCreateOrder_Click);
            // 
            // buttonTakeOrderInWork
            // 
            this.buttonTakeOrderInWork.Location = new System.Drawing.Point(861, 122);
            this.buttonTakeOrderInWork.Name = "buttonTakeOrderInWork";
            this.buttonTakeOrderInWork.Size = new System.Drawing.Size(150, 31);
            this.buttonTakeOrderInWork.TabIndex = 2;
            this.buttonTakeOrderInWork.Text = "Отдать на выполнение";
            this.buttonTakeOrderInWork.UseVisualStyleBackColor = true;
            this.buttonTakeOrderInWork.Click += new System.EventHandler(this.buttonTakeOrderInWork_Click);
            // 
            // buttonOrderReady
            // 
            this.buttonOrderReady.Location = new System.Drawing.Point(861, 193);
            this.buttonOrderReady.Name = "buttonOrderReady";
            this.buttonOrderReady.Size = new System.Drawing.Size(150, 31);
            this.buttonOrderReady.TabIndex = 3;
            this.buttonOrderReady.Text = "Заказ готов";
            this.buttonOrderReady.UseVisualStyleBackColor = true;
            this.buttonOrderReady.Click += new System.EventHandler(this.buttonOrderReady_Click);
            // 
            // buttonIssuedOrder
            // 
            this.buttonIssuedOrder.Location = new System.Drawing.Point(861, 264);
            this.buttonIssuedOrder.Name = "buttonIssuedOrder";
            this.buttonIssuedOrder.Size = new System.Drawing.Size(150, 31);
            this.buttonIssuedOrder.TabIndex = 4;
            this.buttonIssuedOrder.Text = "Заказ выдан";
            this.buttonIssuedOrder.UseVisualStyleBackColor = true;
            this.buttonIssuedOrder.Click += new System.EventHandler(this.buttonIssuedOrder_Click);
            // 
            // buttonRefact
            // 
            this.buttonRefact.Location = new System.Drawing.Point(861, 332);
            this.buttonRefact.Name = "buttonRefact";
            this.buttonRefact.Size = new System.Drawing.Size(150, 31);
            this.buttonRefact.TabIndex = 5;
            this.buttonRefact.Text = "Обновить список";
            this.buttonRefact.UseVisualStyleBackColor = true;
            this.buttonRefact.Click += new System.EventHandler(this.buttonRefact_Click);
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.toolStripMenuItem1,
            this.ToolStripMenuItemFillWarhouse});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(1045, 24);
            this.menuStrip1.TabIndex = 6;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ToolStripMenuItemComponents,
            this.ToolStripMenuItemCanneds,
            this.ToolStripMenuItemWarehouses});
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(94, 20);
            this.toolStripMenuItem1.Text = "Справочники";
            // 
            // ToolStripMenuItemComponents
            // 
            this.ToolStripMenuItemComponents.Name = "ToolStripMenuItemComponents";
            this.ToolStripMenuItemComponents.Size = new System.Drawing.Size(192, 22);
            this.ToolStripMenuItemComponents.Text = "Список компонентов";
            this.ToolStripMenuItemComponents.Click += new System.EventHandler(this.ToolStripMenuItemComponents_Click);
            // 
            // ToolStripMenuItemCanneds
            // 
            this.ToolStripMenuItemCanneds.Name = "ToolStripMenuItemCanneds";
            this.ToolStripMenuItemCanneds.Size = new System.Drawing.Size(192, 22);
            this.ToolStripMenuItemCanneds.Text = "Список изделий";
            this.ToolStripMenuItemCanneds.Click += new System.EventHandler(this.ToolStripMenuItemCanneds_Click);
            // 
            // ToolStripMenuItemWarehouses
            // 
            this.ToolStripMenuItemWarehouses.Name = "ToolStripMenuItemWarehouses";
            this.ToolStripMenuItemWarehouses.Size = new System.Drawing.Size(192, 22);
            this.ToolStripMenuItemWarehouses.Text = "Список складов";
            this.ToolStripMenuItemWarehouses.Click += new System.EventHandler(this.ToolStripMenuItemWarehouses_Click);
            // 
            // ToolStripMenuItemFillWarhouse
            // 
            this.ToolStripMenuItemFillWarhouse.Name = "ToolStripMenuItemFillWarhouse";
            this.ToolStripMenuItemFillWarhouse.Size = new System.Drawing.Size(115, 20);
            this.ToolStripMenuItemFillWarhouse.Text = "Пополнить склад";
            this.ToolStripMenuItemFillWarhouse.Click += new System.EventHandler(this.ToolStripMenuItemFillWarhouse_Click);
            // 
            // FormMain
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1045, 405);
            this.Controls.Add(this.buttonRefact);
            this.Controls.Add(this.buttonIssuedOrder);
            this.Controls.Add(this.buttonOrderReady);
            this.Controls.Add(this.buttonTakeOrderInWork);
            this.Controls.Add(this.buttonCreateOrder);
            this.Controls.Add(this.dataGridView1);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "FormMain";
            this.Text = "Рыбный завод";
            this.Load += new System.EventHandler(this.FormMain_Load);
            ((System.ComponentModel.ISupportInitialize)(this.dataGridView1)).EndInit();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button buttonCreateOrder;
        private System.Windows.Forms.Button buttonTakeOrderInWork;
        private System.Windows.Forms.Button buttonOrderReady;
        private System.Windows.Forms.Button buttonIssuedOrder;
        private System.Windows.Forms.Button buttonRefact;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuItemComponents;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuItemCanneds;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuItemWarehouses;
        private System.Windows.Forms.ToolStripMenuItem ToolStripMenuItemFillWarhouse;
    }
}