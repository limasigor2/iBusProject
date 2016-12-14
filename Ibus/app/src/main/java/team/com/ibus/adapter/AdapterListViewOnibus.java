package team.com.ibus.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import team.com.ibus.Dominio.Onibus;
import team.com.ibus.R;

/**
 * Created by Yuri on 07/12/2016.
 */

public class AdapterListViewOnibus extends BaseAdapter {

    private Context context;
    private Onibus [] listOnibus;

    public AdapterListViewOnibus(Context context, Onibus [] listOnibus){

        this.context = context;
        this.listOnibus = listOnibus;
    }

    @Override
    public int getCount() {
        return listOnibus.length;
    }

    @Override
    public Object getItem(int position) {
        return listOnibus[position];
    }

    @Override
    public long getItemId(int position) {
        return listOnibus[position].getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        //se ainda não estiver sido carregado essa linha
        // inflate the layout for each list row

        if(convertView == null){

            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_list_onibus,viewGroup,false);

            TextView tvId = (TextView) convertView.findViewById(R.id.tv_item_id_onibus);
            TextView tvTitulo = (TextView) convertView.findViewById(R.id.tv_item_titulo_onibus);
            TextView tvPlaca= (TextView) convertView.findViewById(R.id.tv_item_placa_onibus);
            TextView tvCor= (TextView) convertView.findViewById(R.id.tv_item_cor_onibus);

            Onibus onibus = listOnibus[position];

            tvId.setText(onibus.getId()+"");
            tvTitulo.setText("Ônibus " + position + 1);
            tvPlaca.setText(onibus.getPlaca());
            tvCor.setText(onibus.getCor());

        }

        return convertView;
    }

}
