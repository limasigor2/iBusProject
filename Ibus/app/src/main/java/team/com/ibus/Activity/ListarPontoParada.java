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

import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.R;
import team.com.ibus.adapter.AdapterListViewPontoParada;
import team.com.ibus.service.PontoParadaService;

public class ListarPontoParada extends AppCompatActivity {

    ProgressDialog pdialog;
    Context context;
    ListView listViewPontoParada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_ponto_parada);
        context = this;

        final PontoParadaService pontoParadaService = new PontoParadaService(context, pdialog);

        //objeto pronto para ser enviado pelo web service

        try{


            PontoDeParada[] pontoDeParadaList = pontoParadaService.buscarPontoParada();

            AdapterListViewPontoParada adapterListViewPontoParada = new AdapterListViewPontoParada(context, pontoDeParadaList);
            listViewPontoParada.setAdapter(adapterListViewPontoParada );

        }catch (Exception ex){

            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }

        listViewPontoParada = (ListView) findViewById(R.id.listview_ponto_parada);

        listViewPontoParada.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_ponto_parada);
                TextView tvNome = (TextView) view.findViewById(R.id.tv_item_nome_ponto_parada);
                TextView tvEndereco = (TextView) view.findViewById(R.id.tv_item_endereco_ponto_parada);
                TextView tvDescricao = (TextView) view.findViewById(R.id.tv_item_descricao_ponto_parada);

                Bundle bundle = new Bundle();
                bundle.putInt("id_ponto_parada",Integer.parseInt(tvId.getText().toString()));
                bundle.putString("nome_ponto_parada",tvNome.getText().toString());
                bundle.putString("endereco_ponto_parada",tvEndereco.getText().toString());
                bundle.putString("descricao_ponto_parada",tvDescricao.getText().toString());


                Intent intent = new Intent(context, CadastrarPontoParada.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });


        listViewPontoParada.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, final long id) {

                //deletar o onibus aqui.

                TextView tvId = (TextView) view.findViewById(R.id.tv_item_id_ponto_parada);
                TextView tvNome = (TextView) view.findViewById(R.id.tv_item_nome_ponto_parada);
                TextView tvEndereco = (TextView) view.findViewById(R.id.tv_item_endereco_ponto_parada);

                final int idPontoParada = Integer.parseInt(tvId.getText().toString());
                final String nome = tvNome.getText().toString();
                final String endereco = tvEndereco.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Excluir Ponto de Parada")
                        .setMessage("Você tem certeza que quer excluir o ponto de parada : " + nome + " e endereco: " + endereco + " ?")
                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Sim, Excluir", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                PontoDeParada pontoDeParada = new PontoDeParada(idPontoParada, nome, "", endereco, null);

                                String retornoWS = pontoParadaService.deletePontoParada(pontoDeParada);


                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                return true;
            }
        });
    }

}