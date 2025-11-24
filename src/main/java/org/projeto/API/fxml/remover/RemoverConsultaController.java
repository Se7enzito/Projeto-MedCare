package org.projeto.API.fxml.remover;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.projeto.API.FXMLManager;
import org.projeto.database.dao.ConsultaDAO;
import org.projeto.database.model.Consulta;

public class RemoverConsultaController {

    @FXML private ListView<Consulta> listaConsultas;

    private final ConsultaDAO consultaDAO = new ConsultaDAO();

    @FXML
    public void initialize() {
        atualizar();
    }

    @FXML
    public void atualizar() {
        try {
            listaConsultas.getItems().setAll(consultaDAO.getAll());
        } catch (Exception e) {
            alerta("Erro", "Erro ao carregar consultas!");
        }
    }

    @FXML
    private void removerSelecionada() {
        Consulta selecionada = listaConsultas.getSelectionModel().getSelectedItem();

        if (selecionada == null) {
            alerta("Aviso", "Selecione uma consulta para remover.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setHeaderText("Deseja mesmo remover?");
        confirm.setContentText("Essa ação é permanente.");

        if (confirm.showAndWait().get() == ButtonType.OK) {
            try {
                consultaDAO.delete(selecionada.getId());
                atualizar();
                alerta("Sucesso", "Consulta removida com sucesso!");
            } catch (Exception e) {
                alerta("Erro", "Erro ao remover!");
            }
        }
    }

    @FXML
    private void voltar() {
        FXMLManager.showScene("inicial.fxml", "MedCare");
    }

    private void alerta(String t, String m) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText(t);
        a.setContentText(m);
        a.show();
    }
}
