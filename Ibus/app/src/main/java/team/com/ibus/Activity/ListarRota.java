package team.com.ibus.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.Dominio.Rota;
import team.com.ibus.R;
import team.com.ibus.adapter.AdapterListViewRota;
import team.com.ibus.service.RotaService;

public class ListarRota extends AppCompatActivity {

    ProgressDialog pdialog;
    Context context;
    ListView listViewRota;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_rota);
        context = this;

        listViewRota = (ListView) findViewById(R.id.listview_rota);

        try{

            // invoking the OnibusWSAsync AsyncTask
            RotaService rotaService = new RotaService(context,pdialog);
            Rota[] listRota = rotaService.buscarRota();


            AdapterListViewRota adapterListViewRota = new AdapterListViewRota(context,listRota);
            listViewRota.setAdapter(adapterListViewRota);

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }


        listViewRota.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_rota);
                TextView tvTitulo = (TextView) view.findViewById(R.id.tv_item_titulo_rota);
                TextView tvOrigem = (TextView) view.findViewById(R.id.tv_item_origem_rota);
                TextView tvDestino = (TextView) view.findViewById(R.id.tv_item_destino_rota);

                Bundle bundle = new Bundle();
                bundle.putInt("id_rota",Integer.parseInt(tvId.getText().toString()));
                bundle.putString("titulo_rota",tvTitulo.getText().toString());
                bundle.putString("origem_rota",tvOrigem.getText().toString());
                bundle.putString("destino_rota",tvDestino.getText().toString());


                Intent intent = new Intent(context, CadastrarPontoParada.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        listViewRota.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {

                //deletar o onibus aqui.

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_rota);
                TextView tvTitulo = (TextView) view.findViewById(R.id.tv_item_titulo_rota);
                TextView tvOrigem = (TextView) view.findViewById(R.id.tv_item_origem_rota);
                TextView tvDestino = (TextView) view.findViewById(R.id.tv_item_destino_rota);

                final int idRota = Integer.parseInt(tvId.getText().toString());
                final String titulo = tvTitulo.getText().toString();
                final String origem = tvOrigem.getText().toString();
                final String destino = tvDestino.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Excluir Rota")
                        .setMessage("Você tem certeza que quer excluir a rota: " + titulo + " com :\nOrigem - " + origem+ " \nDestino = " + destino + " ?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Sim, Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                //só preciso passar um objeto com apenas o id, pois no delete do web service, ele só precisa do id
                                //e eu sei que o objeto está completo aqui desse lado.
                                Gson gson = new Gson();
                                Rota rota = new Rota(idRota);
                                RotaService rotaService = new RotaService(context,pdialog);

                                String retornoWS = rotaService.deleteRota(rota);

                                //TODO fazer alguma verificação com o retorno do método

                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

}
