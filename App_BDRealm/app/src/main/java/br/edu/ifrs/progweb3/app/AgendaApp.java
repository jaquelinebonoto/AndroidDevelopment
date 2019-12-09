package br.edu.ifrs.progweb3.app;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

//Classe que herda de Application -> usada para configurar a Realm na aplicação
//deve ser indicada no manifesto, por exemplo:
// <application
//            android:name=".app.AgendaApp"
public class AgendaApp extends Application{

    private static AgendaApp agendaInstance;

    public static AgendaApp getInstance() {
        return agendaInstance;
    }

    @Override

    public void onCreate() {
        super.onCreate();
        // Inicializa o Realm. Isso deve ser feito somente quando a aplicação inicia
        Realm.init(this);
        //define o nome do esquema, a versão, e como será a migração
        //a linha new RealmConfiguration.Builder().build(); cria por padrão um arquivo chamado "default.realm"
        // localizado em Context.getFilesDir(). No caso abaixo o nome do arquivo será tarefas.realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("tarefas.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded() //deleteRealmIfMigrationNeeded() -> se você mudar a versao do banco com essa linha você apaga todas as informações da versão anterior
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public void onTerminate() {
        //finaliza o Realm quando encerra a execução do app
        Realm.getDefaultInstance().close();
        super.onTerminate();
    }
}