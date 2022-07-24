package Controller;

import Model.Persona;
import Model.PersonaDAO;
import View.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Controller implements ActionListener {

    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    Vista vista = new Vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controller(Vista v) {
        this.vista = v;
        this.vista.list.addActionListener(this);
        this.vista.save.addActionListener(this);
        this.vista.edit.addActionListener(this);
        this.vista.ok.addActionListener(this);
        this.vista.delete.addActionListener(this);
        listar(vista.table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.list) {
            limpiar();
            listar(vista.table);
        }
        if (e.getSource() == vista.save) {
            agregar();
            limpiar();
            listar(vista.table);
        }
        if (e.getSource() == vista.edit) {
            int fila = vista.table.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una fila");
            } else {
                int id = Integer.parseInt(vista.table.getValueAt(fila, 0).toString());
                String name = vista.table.getValueAt(fila, 1).toString();
                String email = vista.table.getValueAt(fila, 2).toString();
                String phone = vista.table.getValueAt(fila, 3).toString();

                vista.id.setText("" + id);
                vista.name.setText(name);
                vista.email.setText(email);
                vista.phone.setText(phone);
            }
        }
        if (e.getSource() == vista.ok) {
            update();
            limpiar();
            listar(vista.table);
        }
        if (e.getSource() == vista.delete) {
            int fila = vista.table.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Seleccione una fila");
            } else {
                int r = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar este usuario?");
                if (r == 0) {
                    int id = Integer.parseInt(vista.table.getValueAt(fila, 0).toString());
                    delete(id);
                    limpiar();
                    listar(vista.table);
                }
            }
        }
    }

    public void listar(JTable table) {
        modelo = (DefaultTableModel) table.getModel();
        List<Persona> lista = dao.listar();
        Object[] object = new Object[4];

        for (int i = 0; i < lista.size(); i++) {
            object[0] = lista.get(i).getId();
            object[1] = lista.get(i).getName();
            object[2] = lista.get(i).getEmail();
            object[3] = lista.get(i).getPhone();
            modelo.addRow(object);
        }
        vista.table.setModel(modelo);
    }

    public void agregar() {
        String name = vista.name.getText();
        String email = vista.email.getText();
        String phone = vista.phone.getText();

        p.setName(name);
        p.setEmail(email);
        p.setPhone(phone);
        dao.store(p);
        JOptionPane.showMessageDialog(vista, "Usuario agregado con exito");

    }

    public void update() {
        int id = Integer.parseInt(vista.id.getText());
        String name = vista.name.getText();
        String email = vista.email.getText();
        String phone = vista.phone.getText();

        p.setId(id);
        p.setName(name);
        p.setEmail(email);
        p.setPhone(phone);
        dao.update(p);

        JOptionPane.showMessageDialog(vista, "Usuario actualizado con exito");

    }

    public void delete(int id) {
        p.setId(id);
        dao.delete(id);
        JOptionPane.showMessageDialog(vista, "Usuario eliminado correctamente");

    }

    public void limpiar() {
        for (int i = 0; i < vista.table.getRowCount(); i++) {
            modelo.removeRow(i);
            i--;
        }
    }

}
