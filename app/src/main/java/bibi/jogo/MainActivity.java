package bibi.jogo;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private int topo;
    private ImageView obj;
    private TextView txt;
    private int ponto;
    private TextView vida;
    private int contVidas = 3;
    private ImageView gameOver;
    private ImageView vida1;
    private ImageView vida2;
    private ImageView vida3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // move objeto
        obj = findViewById(R.id.objeto);
        txt = findViewById(R.id.texto);
        vida = findViewById(R.id.vidas);
        gameOver = findViewById(R.id.gameover);
        vida1 = findViewById(R.id.v1);
        vida2 = findViewById(R.id.v2);
        vida3 = findViewById(R.id.v3);

        // pega tamanho tela
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int alturatela = displayMetrics.heightPixels;
        int larguratela = displayMetrics.widthPixels  - obj.getLayoutParams().width;

        // intervalo tempo
        Handler tempo = new Handler();
        tempo.postDelayed(new Runnable() {
            @Override
            public void run() {
                topo += 80;
                obj.setTranslationY(topo);

                if(topo >= alturatela){
                    topo = -obj.getLayoutParams().height;
                    int lado = (int)(Math.random() * larguratela);
                    obj.setTranslationX(lado);

                    contVidas--;
                    vida.setText("Vidas: "+contVidas);

                }
                if(contVidas == 2){
                    vida1.setVisibility(View.GONE);
                }

                if(contVidas == 1){
                    vida2.setVisibility(View.GONE);
                }

                if(contVidas == 0){
                    vida3.setVisibility(View.GONE);
                }

                if(contVidas > 0){
                    tempo.postDelayed(this,100);
                }else{
                    gameOver.setVisibility(View.VISIBLE);
                    Toast alerta = Toast.makeText(getApplicationContext(),"Perdeu", Toast.LENGTH_LONG);
                    alerta.show();
                }
            }
        }, 100);

        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topo = -obj.getLayoutParams().height;
                obj.setTranslationY(topo);

                int lado = (int)(Math.random() * larguratela);
                obj.setTranslationX(lado);

                ponto++;
                txt.setText("pontos: "+ponto);
            }
        });
    }
}