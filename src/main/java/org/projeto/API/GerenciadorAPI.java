package org.projeto.API;

public class GerenciadorAPI {

    public void runApp() {
        System.out.println("Carregando interface gráfica...");
        iniciarInterfaceFXML();
    }

    private void iniciarInterfaceFXML() {
        try {
            FXMLManager.showScene("login.fxml", "MedCare");
        } catch (Exception e) {
            System.out.println("Erro ao carregar interface FXML:");
            e.printStackTrace();
        }
    }

}
