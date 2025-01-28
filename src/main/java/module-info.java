module dam.JosantVarona {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires jdk.compiler;

    opens dam.JosantVarona.model to org.hibernate.orm.core;

    opens dam.JosantVarona to javafx.fxml;
    opens dam.JosantVarona.View to javafx.fxml;

    exports dam.JosantVarona;
    exports dam.JosantVarona.View;
    exports dam.JosantVarona.model;
}
