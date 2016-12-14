package team.com.ibus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.R;

/**
 * Created by Yuri on 07/12/2016.
 */

public class AdapterListViewPontoParada extends BaseAdapter {

    private Context context;
    private PontoDeParada [] listPontoParada;

    public AdapterListViewPontoParada(Context context, PontoDeParada [] listPontoParada){

        this.context = context;
        this.listPontoParada = listPontoParada;
    }

    @Override
    public int getCount() {
        return listPontoParada.length;
    }

    @Override
    public Object getItem(int position) {
        return listPontoParada[position];
    }

    @Override
    public long getItemId(int position) {
        return listPontoParada[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //se ainda não estiver sido carregado essa linha
        // inflate the layout for each list row

        if(convertView == null){

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_ponto_parada,viewGroup,false);

            TextView tvId = (TextView) convertView.findViewById(R.id.tv_item_id_ponto_parada);
            TextView tvNome = (TextView) convertView.findViewById(R.id.tv_item_nome_ponto_parada);
            TextView tvEndereco = (TextView) convertView.findViewById(R.id.tv_item_endereco_ponto_parada);
            TextView tvDescricao = (TextView) convertView.findViewById(R.id.tv_item_descricao_ponto_parada);

            PontoDeParada pontoDeParada = listPontoParada[position];

            tvId.setText(pontoDeParada.getId()+"");
            tvNome.setText(pontoDeParada.getNome());
            tvEndereco.setText(pontoDeParada.getEndereco());
            tvDescricao.setText(pontoDeParada.getDescricao());

        }

        return convertView;
    }

}
