package com.example.fcauserano.digitalnews.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fcauserano.digitalnews.R;
import com.example.fcauserano.digitalnews.model.POJO.Noticia;
import com.example.fcauserano.digitalnews.utils.GlideApp;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter {

    private List<Noticia> noticias;
    private NotificadorNoticiaCelda notificadorNoticiaCelda;
    private Context context;

    public NoticiaAdapter(List<Noticia> noticias,NotificadorNoticiaCelda notificadorNoticiaCelda, Context context) {
        this.noticias = noticias;
        this.notificadorNoticiaCelda = notificadorNoticiaCelda;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.celda_noticia_portada_home, parent, false);
        ViewHolderNoticia viewHolderNoticiaDePortada = new ViewHolderNoticia(view);

        return viewHolderNoticiaDePortada;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Noticia noticia_de_portada = noticias.get(position);
        ViewHolderNoticia viewHolderNoticiaDePortada = (ViewHolderNoticia) holder;
        viewHolderNoticiaDePortada.cargarNoticia(noticia_de_portada);
    }

    @Override
    public int getItemCount() {
        if (noticias != null) {
            return noticias.size();
        } else {
            return 0;
        }
    }

// hice esto para que me muestre el recycler que lo tengo que pegar en el fragment

    private class ViewHolderNoticia extends RecyclerView.ViewHolder {

        private TextView titulo_noticia;
        private TextView texto_noticia;
        private TextView categoria_noticia;
        private ImageView imagen_noticia;
        private  LinearLayout linearLayout;

        public ViewHolderNoticia(View itemView) {
            super(itemView);
            titulo_noticia = itemView.findViewById(R.id.portada_titulo_noticia);
            texto_noticia = itemView.findViewById(R.id.portada_texto_noticia);
            categoria_noticia = itemView.findViewById(R.id.portada_categoria_noticia);
            imagen_noticia = itemView.findViewById(R.id.portada_imagen_noticia);
            linearLayout = itemView.findViewById(R.id.portada_linearlayout_noticia);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int posicionNoticiaClikeda = getAdapterPosition();
                    Noticia noticia = noticias.get(posicionNoticiaClikeda);
                    notificadorNoticiaCelda.noticiaClikeada(noticia);
                }
            });

            categoria_noticia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //int posicionNoticiaClikeda = getAdapterPosition();
                    //Noticia noticia = noticias.get(posicionNoticiaClikeda);
                    String fuente = categoria_noticia.getText().toString();
                    //notificadorNoticiaCelda.categoriaSeleccionada(noticia.getCategoria());
                    notificadorNoticiaCelda.fuenteSeleccionada(fuente);
                }
            });
        }

        public void cargarNoticia(Noticia noticia_de_portada) {
            titulo_noticia.setText(noticia_de_portada.getTitulo());
            texto_noticia.setText(noticia_de_portada.getTexto());
            categoria_noticia.setText(noticia_de_portada.getFuente().getNombre());
            //categoria_noticia.setText(noticia_de_portada.getCategoria());
            GlideApp.with(itemView.getContext())
                    .load(noticia_de_portada.getImagen())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.iso_para_arranque)
                    .into(imagen_noticia);
            //imagen_noticia.setImageResource(noticia_de_portada.getDrawable(context));
        }
    }
    //INTERFAZ QUE COMUNICA ADAPTER CON FRAGMENT. EL FRAGMENT ES QUIEN IMPLEMENTA ESTA INTERFAZ
    public interface NotificadorNoticiaCelda {
        public void noticiaClikeada (Noticia noticia);
        //public void categoriaClikeada (int categoria);
        public void categoriaSeleccionada (String categoria);
        public void fuenteSeleccionada(String fuente);

    }

    public void actualizarNoticias(List<Noticia> noticias){
        this.noticias.addAll(noticias);
        //this.noticias = noticias;
        notifyDataSetChanged();
    }
    public void actualizarNoticiasBusqueda(List<Noticia> noticias){
        this.noticias = noticias;
        notifyDataSetChanged();
    }
}





