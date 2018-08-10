package com.example.fcauserano.digitalnews.views.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class CategoriaNoticiasAdapter extends RecyclerView.Adapter implements Filterable{
    private List<Noticia> noticias;
    private  List<Noticia> noticiatListFiltered;
    private Context context;
    private NotificadorNoticiaCelda notificadorNoticiaCelda;

    public CategoriaNoticiasAdapter(List<Noticia> noticias, Context context,NotificadorNoticiaCelda notificadorNoticiaCelda) {
        this.noticias = noticias;
        this.noticiatListFiltered = noticias;
        this.context = context;
        this.notificadorNoticiaCelda = notificadorNoticiaCelda;
       }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_recycler_categoria_noticia, parent, false);
        ViewHolderDetallesNoticias viewHolderDetallesNoticias = new ViewHolderDetallesNoticias(view);
        return viewHolderDetallesNoticias;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Noticia noticia = noticiatListFiltered.get(position);
        ViewHolderDetallesNoticias viewHolderDetallesNoticias = (ViewHolderDetallesNoticias) holder;
        viewHolderDetallesNoticias.cargarNoticia(noticia);
    }

    @Override
    public int getItemCount() {
        if (noticiatListFiltered != null) {
            return noticiatListFiltered.size();
        } else {
            return 0;
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();

                if (charString.isEmpty()) {
                    noticiatListFiltered = noticias;
                }
                else {
                    List<Noticia> filteredList = new ArrayList<>();
                    for (Noticia row : noticias) {
                        if (row.getTitulo().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    noticiatListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = noticiatListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                noticiatListFiltered = (ArrayList<Noticia>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolderDetallesNoticias extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textViewTitulo;
        private TextView textViewNoticia;
        private CardView cardView;


        public ViewHolderDetallesNoticias(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView2);
            textViewTitulo = itemView.findViewById(R.id.textView2);
            textViewNoticia = itemView.findViewById(R.id.textView3);
            cardView = itemView.findViewById(R.id.celda_recycler_categoria_noticia_cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<Noticia> listNoticia = noticias;
                    int posicionclikeada = getAdapterPosition();
                    notificadorNoticiaCelda.notificarCeldaClickeado(posicionclikeada,listNoticia);
                }
            });

        }

        public void cargarNoticia(Noticia noticia) {
            GlideApp.with(itemView.getContext())
                    .load(noticia.getImagen())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.iso_para_arranque)
                    .into(imageView);
            //imageView.setImageResource(noticia.getDrawable(context));
            textViewTitulo.setText(noticia.getTitulo());
            textViewNoticia.setText(noticia.getTexto());
        }
    }
    public void actualizarNoticias(List<Noticia> noticias){
        //this.noticias.addAll(noticias);
        this.noticias = noticias;
        this.noticiatListFiltered = noticias;
        notifyDataSetChanged();
    }

    //INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
    public interface NotificadorNoticiaCelda {
        public void notificarCeldaClickeado(int posicionclikeada,List<Noticia> noticias);
    }


}
