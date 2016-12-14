package team.com.ibus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import team.com.ibus.Dominio.PontoDeParada;
import team.com.ibus.Dominio.Rota;
import team.com.ibus.R;

/**
 * Created by Yuri on 07/12/2016.
 */

public class AdapterListViewRota extends BaseAdapter {

    Context context;
    Rota listRota[];

    public AdapterListViewRota(Context context, Rota listRota[]){

        this.context = context;
        this.listRota = listRota;

    }

    @Override
    public int getCount() {
        return listRota.length;
    }

    @Override
    public Object getItem(int position) {
        return listRota[position];
    }

    @Override
    public long getItemId(int position) {
        return listRota[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        if(convertView == null){

            LayoutInflater.from(context)
                    .inflate(R.layout.item_list_rota,viewGroup,false);

            TextView tvId = (TextView) convertView.findViewById(R.id.tv_item_id_rota);
            TextView tvTitulo = (TextView) convertView.findViewById(R.id.tv_item_titulo_rota);
            TextView tvOrigem = (TextView) convertView.findViewById(R.id.tv_item_origem_rota);
            TextView tvDestino = (TextView) convertView.findViewById(R.id.tv_item_destino_rota);

            Rota rota = listRota[position];

            String destino, origem;

            if(rota.getPosicaoOrigem().getLatitude() == -4.970186 && rota.getPosicaoOrigem().getLatitude() == -39.015866){
                origem = "Praça do Leão";
                destino = "IFCE";
            }else{
                origem = "IFCE";
                destino = "Praça do Leão";
            }

            tvId.setText(rota.getId()+"");
            tvTitulo.setText("Rota " + position);
            tvOrigem.setText(origem);
            tvDestino.setText(destino);

        }

        return convertView;
    }
}
