package com.example.demo.entidades;

import java.util.ArrayList;
import java.util.List;
//import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Controller;

import com.example.demo.repositorio.RepositorioMascotas;
import com.example.demo.repositorio.RepositorioUsuarios;
import com.example.demo.repositorio.RepositorioVeterinarios;
import com.example.demo.repositorio.RepositorioTratamiento;
import com.example.demo.repositorio.RepositorioMedicamento;

import jakarta.transaction.Transactional;
@Controller
@Transactional
public class DatabaseInit implements ApplicationRunner {
    @Autowired
    RepositorioMascotas mascotaRepository;

    @Autowired
    RepositorioUsuarios usuarioRepository;

    @Autowired
    RepositorioVeterinarios veterinarioRepository;

    @Autowired
    RepositorioTratamiento tratamientoRepository;

    @Autowired
    RepositorioMedicamento medicamentoRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        
        Usuario u1 = new Usuario( "Andres Garcia", "123456789", "juan@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7", 1 );
        Usuario u2= new Usuario( "Alvaro Morata", "987654321", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",2 );
        Usuario u3 = new Usuario( "Juan Castro", "555555555", "pablo@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",3 );
        Usuario u4 =new Usuario( "Omar Perez", "444444444", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",4 );
        Usuario u5 =new Usuario( "Luis Manuel Seijas", "333333333", "lucia@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",5 );
        Usuario u6 =new Usuario( "Pablo Hernandez", "222222222", "maria@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",6 );
        Usuario u7 =new Usuario( "Andres Colmenares", "111111111", "lucia@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",7 );
        Usuario u8 =new Usuario( "Esteban Lopez", "666666666", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",8 );
        Usuario u9 =new Usuario( "Simba", "999999999", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",9 );
        Usuario u10 =new Usuario( "Estefania Alba", "000000000", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",10 );
        Usuario u11 = new Usuario( "Margarita Castro", "888888888", "carlos@example.com","1234", "https://thfvnext.bing.com/th/id/OIP.i4YH9hPP_yM3nXMIQDDkQwHaLL?w=118&h=180&c=7&r=0&o=5&cb=thfvnext&pid=1.7",11 );
        Usuario u12 = new Usuario("Maria Lopez", "987654321", "maria@example.com", "abcd1234", "https://randomuser.me/api/portraits/women/2.jpg",12 );
        Usuario u13 = new Usuario("Carlos Martinez", "1122334455", "carlos@example.com", "pass123", "https://randomuser.me/api/portraits/men/3.jpg",13 );
        Usuario u14 = new Usuario("Laura Hernandez", "2233445566", "laura@example.com", "laura123", "https://randomuser.me/api/portraits/women/4.jpg",14 );
        Usuario u15 = new Usuario("Jorge Ramirez", "3344556677", "jorge@example.com", "jorgepass", "https://randomuser.me/api/portraits/men/5.jpg",15 );
        Usuario u16 = new Usuario("Ana Torres", "4455667788", "ana@example.com", "ana2024", "https://randomuser.me/api/portraits/women/6.jpg",16 );
        Usuario u17 = new Usuario("David Gutierrez", "5566778899", "david@example.com", "davidpass", "https://randomuser.me/api/portraits/men/7.jpg",17 );
        Usuario u18 = new Usuario("Paola Jimenez", "6677889900", "paola@example.com", "paola2024", "https://randomuser.me/api/portraits/women/8.jpg",18 );
        Usuario u19 = new Usuario("Felipe Castro", "7788990011", "felipe@example.com", "felipepass", "https://randomuser.me/api/portraits/men/9.jpg",19 );
        Usuario u20 = new Usuario("Sofia Vargas", "8899001122", "sofia@example.com", "sofia2024", "https://randomuser.me/api/portraits/women/10.jpg",20 );
        Usuario u21 = new Usuario("Andres Torres", "9900112233", "andrest@example.com", "andrespass", "https://randomuser.me/api/portraits/men/11.jpg",21 );
        Usuario u22 = new Usuario("Valentina Moreno", "1011121314", "valentina@example.com", "valen123", "https://randomuser.me/api/portraits/women/12.jpg",22 );
        Usuario u23 = new Usuario("Ricardo Peña", "1213141516", "ricardo@example.com", "ricardopass", "https://randomuser.me/api/portraits/men/13.jpg",23 );
        Usuario u24 = new Usuario("Camila Rojas", "1415161718", "camila@example.com", "camila123", "https://randomuser.me/api/portraits/women/14.jpg",24 );
        Usuario u25 = new Usuario("Mateo Diaz", "1617181920", "mateo@example.com", "mateopass", "https://randomuser.me/api/portraits/men/15.jpg",25 );
        Usuario u26 = new Usuario("Isabella Ruiz", "1819202122", "isabella@example.com", "isa2024", "https://randomuser.me/api/portraits/women/16.jpg",26 );
        Usuario u27 = new Usuario("Sebastian Reyes", "2021222324", "sebastian@example.com", "sebas123", "https://randomuser.me/api/portraits/men/17.jpg",27 );
        Usuario u28 = new Usuario("Natalia Silva", "2223242526", "natalia@example.com", "natalia123", "https://randomuser.me/api/portraits/women/18.jpg",28 );
        Usuario u29 = new Usuario("Juan Perez", "2425262728", "juanp@example.com", "juanpass", "https://randomuser.me/api/portraits/men/19.jpg",29 );
        Usuario u30 = new Usuario("Gabriela Fernandez", "2627282930", "gabriela@example.com", "gaby2024", "https://randomuser.me/api/portraits/women/20.jpg",30 );
        Usuario u31 = new Usuario("Oscar Molina", "2829303132", "oscar@example.com", "oscarpass", "https://randomuser.me/api/portraits/men/21.jpg",31 );
        Usuario u32 = new Usuario("Daniela Castro", "3031323334", "daniela@example.com", "danipass", "https://randomuser.me/api/portraits/women/22.jpg",32 );
        Usuario u33 = new Usuario("Santiago Vargas", "3233343536", "santiago@example.com", "santi123", "https://randomuser.me/api/portraits/men/23.jpg",33 );
        Usuario u34 = new Usuario("Fernanda Pineda", "3435363738", "fernanda@example.com", "fer123", "https://randomuser.me/api/portraits/women/24.jpg",34 );
        Usuario u35 = new Usuario("Alejandro Ortiz", "3637383940", "alejandro@example.com", "alejo123", "https://randomuser.me/api/portraits/men/25.jpg",35 );
        Usuario u36 = new Usuario("Lucia Camargo", "3839404142", "lucia@example.com", "luciapass", "https://randomuser.me/api/portraits/women/26.jpg",36 );
        Usuario u37 = new Usuario("Hugo Herrera", "4041424344", "hugo@example.com", "hugopass", "https://randomuser.me/api/portraits/men/27.jpg",37 );
        Usuario u38 = new Usuario("Marta Gonzalez", "4243444546", "marta@example.com", "marta2024", "https://randomuser.me/api/portraits/women/28.jpg",38 );
        Usuario u39 = new Usuario("Pedro Suarez", "4445464748", "pedro@example.com", "pedropass", "https://randomuser.me/api/portraits/men/29.jpg",39 );
        Usuario u40 = new Usuario("Angela Romero", "4647484950", "angela@example.com", "angelapass", "https://randomuser.me/api/portraits/women/30.jpg",40 );
        Usuario u41 = new Usuario("Luis Navarro", "4849505152", "luis@example.com", "luis123", "https://randomuser.me/api/portraits/men/31.jpg",41 );
        Usuario u42 = new Usuario("Catalina Soto", "5051525354", "catalina@example.com", "cata2024", "https://randomuser.me/api/portraits/women/32.jpg",42 );
        Usuario u43 = new Usuario("Manuel Vargas", "5253545556", "manuel@example.com", "manuelpass", "https://randomuser.me/api/portraits/men/33.jpg",43 );
        Usuario u44 = new Usuario("Patricia Mejia", "5455565758", "patricia@example.com", "patypassword", "https://randomuser.me/api/portraits/women/34.jpg",44 );
        Usuario u45 = new Usuario("Rafael Diaz", "5657585960", "rafael@example.com", "rafa2024", "https://randomuser.me/api/portraits/men/35.jpg",45 );
        Usuario u46 = new Usuario("Sandra Cabrera", "5859606162", "sandra@example.com", "sandrapass", "https://randomuser.me/api/portraits/women/36.jpg",46 );
        Usuario u47 = new Usuario("Mauricio Vega", "6061626364", "mauricio@example.com", "mauro123", "https://randomuser.me/api/portraits/men/37.jpg",47);
        Usuario u48 = new Usuario("Veronica Salazar", "6263646566", "veronica@example.com", "vero2024", "https://randomuser.me/api/portraits/women/38.jpg",48);
        Usuario u49 = new Usuario("Diego Cardenas", "6465666768", "diego@example.com", "diegopass", "https://randomuser.me/api/portraits/men/39.jpg",49);
        Usuario u50 = new Usuario("Paula Mendoza", "6667686970", "paula@example.com", "paulapass", "https://randomuser.me/api/portraits/women/40.jpg",50);
                

        usuarioRepository.save(u1);
        usuarioRepository.save(u2);
        usuarioRepository.save(u3);
        usuarioRepository.save(u4);
        usuarioRepository.save(u5);
        usuarioRepository.save(u6);
        usuarioRepository.save(u7);
        usuarioRepository.save(u8);
        usuarioRepository.save(u9);
        usuarioRepository.save(u10);
        usuarioRepository.save(u11);
        usuarioRepository.save(u12);
        usuarioRepository.save(u13);
        usuarioRepository.save(u14);
        usuarioRepository.save(u15);
        usuarioRepository.save(u16);
        usuarioRepository.save(u17);
        usuarioRepository.save(u18);
        usuarioRepository.save(u19);
        usuarioRepository.save(u20);
        usuarioRepository.save(u21);
        usuarioRepository.save(u22);
        usuarioRepository.save(u23);
        usuarioRepository.save(u24);
        usuarioRepository.save(u25);
        usuarioRepository.save(u26);
        usuarioRepository.save(u27);
        usuarioRepository.save(u28);
        usuarioRepository.save(u29);
        usuarioRepository.save(u30);
        usuarioRepository.save(u31);
        usuarioRepository.save(u32);
        usuarioRepository.save(u33);
        usuarioRepository.save(u34);
        usuarioRepository.save(u35);
        usuarioRepository.save(u36);
        usuarioRepository.save(u37);
        usuarioRepository.save(u38);
        usuarioRepository.save(u39);
        usuarioRepository.save(u40);
        usuarioRepository.save(u41);
        usuarioRepository.save(u42);
        usuarioRepository.save(u43);
        usuarioRepository.save(u44);
        usuarioRepository.save(u45);
        usuarioRepository.save(u46);
        usuarioRepository.save(u47);
        usuarioRepository.save(u48);
        usuarioRepository.save(u49);
        usuarioRepository.save(u50);

        mascotaRepository.save(new Mascota(List.of("rabia", "leucemia"),List.of("ninguna conocida"),"Sana", "https://cdn.pixabay.com/photo/2018/09/14/07/57/yellow-lab-3676436_960_720.jpg","Fido", "Perro", "Labrador", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-10"),java.sql.Date.valueOf("2020-01-01"), 30.5f, 123456));
        mascotaRepository.save( new Mascota( List.of("rabia","triple felina", "leucemia"),List.of("ninguna conocida"), "Sana","https://images.unsplash.com/photo-1592194996308-7b43878e84a6", "Whiskers", "Gato", "Siamés", "Hembra", "Saludable",java.sql.Date.valueOf("2025-08-1"),java.sql.Date.valueOf("2019-06-15"), 10.2f, 654321));
        mascotaRepository.save(new Mascota( List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://th.bing.com/th/id/OIP.-rfTw4F7s2A23HkQEOFC9wHaLH?w=139&h=208&c=7&r=0&o=7&pid=1.7&rm=3","Polly", "Loro", "Verde", "Hembra", "Saludable",java.sql.Date.valueOf("2025-08-9"),java.sql.Date.valueOf("2021-03-10"), 1.5f, 789012));
        mascotaRepository.save(new Mascota( List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://www.monaconatureencyclopedia.com/wp-content/uploads/2008/08/3-Amphiprion-ocellaris-%C2%A9-Benoit-Lallement.jpg","Nemo", "Pez", "Payaso", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-9"),java.sql.Date.valueOf("2022-07-20"), 0.3f, 345678));
        mascotaRepository.save(new Mascota(List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://images.unsplash.com/photo-1583511655857-d19b40a7a54e","Max", "Perro", "Bulldog", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-14"),java.sql.Date.valueOf("2018-11-05"), 25.0f, 987654));
        mascotaRepository.save(new Mascota(List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://images.unsplash.com/photo-1592194996308-7b43878e84a6","Luna", "Gato", "Persa", "Hembra","Saludable",java.sql.Date.valueOf("2025-08-10"), java.sql.Date.valueOf("2020-02-28"), 12.0f, 456789));
        mascotaRepository.save(new Mascota( List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://cdn.pixabay.com/photo/2018/05/21/01/24/parrot-3417217_640.jpg","Coco", "Loro", "Azul", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-12"),java.sql.Date.valueOf("2021-05-15"), 1.8f, 159753));
        mascotaRepository.save( new Mascota(   List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://cdn.pixabay.com/photo/2020/02/28/20/10/pallet-doctor-fish-4888454_640.jpg","Dory", "Pez", "Cirujano", "Hembra", "En tratamiento",java.sql.Date.valueOf("2025-08-2"),java.sql.Date.valueOf("2022-08-30"), 0.4f, 852963));
        mascotaRepository.save(new Mascota(   List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://cdn.britannica.com/80/29280-050-A3A13277/Beagles-pets.jpg?w=300","Bella", "Perro", "Beagle", "Hembra", "En tratamiento",java.sql.Date.valueOf("2025-08-7"),java.sql.Date.valueOf("2019-09-12"), 20.0f, 741852));
        mascotaRepository.save(new Mascota(  List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://cdn.pixabay.com/photo/2021/01/15/17/37/cat-5919989_640.jpg", "Simba", "Gato","Maine Coon", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-9"),java.sql.Date.valueOf("2020-05-20"), 15.0f, 963852));
        mascotaRepository.save(new Mascota(   List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://th.bing.com/th/id/OIP.cOOc3HN0kTST8IXGjmYj0AHaNL?w=184&h=328&c=7&r=0&o=7&pid=1.7&rm=3","Kiara", "Loro", "Rojo", "Hembra", "Saludable",java.sql.Date.valueOf("2025-08-9"),java.sql.Date.valueOf("2021-08-25"), 2.0f, 357159));
        mascotaRepository.save(new Mascota(   List.of( "leucemia"),List.of("ninguna conocida"), "Sano","https://media.istockphoto.com/id/149081471/photo/blow-fish-frontal-view.jpg?s=1024x1024&w=is&k=20&c=OvzmoJ2Msj1In1Xl_BbfKnJSLFNs7sjwbtvu231bs6M=","Bubbles", "Pez", "Globo", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-9"),java.sql.Date.valueOf("2022-09-10"), 0.5f, 159753));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Con alergias","https://cdn.pixabay.com/photo/2017/09/25/13/12/dog-2785074_960_720.jpg","Luna","Perro","Beagle","Hembra","Saludable",java.sql.Date.valueOf("2025-07-20"),java.sql.Date.valueOf("2019-05-15"),12.3f,100002));
        mascotaRepository.save(new Mascota(  List.of("rabia","parvovirus"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2020/04/13/20/48/dog-5040008_640.jpg","Rocky","Perro","Bulldog","Macho","Saludable",java.sql.Date.valueOf("2025-06-12"),java.sql.Date.valueOf("2018-03-12"),22.1f,100003));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2017/02/15/12/12/cat-2068462_1280.jpg","Mishi","Gato","Siames","Hembra","Saludable",java.sql.Date.valueOf("2025-09-01"),java.sql.Date.valueOf("2021-02-10"),4.2f,100004));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("asma"), "Tratamiento","https://cdn.pixabay.com/photo/2025/05/07/21/35/golden-retriever-amber-blue-9586001_640.jpg","Toby","Perro","Golden Retriever","Macho","Saludable",java.sql.Date.valueOf("2025-03-11"),java.sql.Date.valueOf("2017-06-20"),28.9f,100005));
        mascotaRepository.save(new Mascota(  List.of("triple felina","rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2017/02/24/16/30/cat-2095559_640.jpg","Nina","Gato","Persa","Hembra","En tratamiento",java.sql.Date.valueOf("2025-11-05"),java.sql.Date.valueOf("2018-08-30"),3.6f,100006));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Con dieta especial","https://cdn.pixabay.com/photo/2019/12/24/13/20/dogs-4716735_640.jpg","Max","Perro","Cocker Spaniel","Macho","En tratamiento",java.sql.Date.valueOf("2025-10-14"),java.sql.Date.valueOf("2020-04-11"),14.0f,100007));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("dermatitis"), "En tratamiento","https://cdn.pixabay.com/photo/2016/10/09/21/03/dog-1726930_640.jpg","Bruno","Perro","Pastor Alemán","Macho","Saludable",java.sql.Date.valueOf("2025-05-09"),java.sql.Date.valueOf("2016-09-15"),35.2f,100008));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2023/06/29/12/28/cats-8096304_640.jpg","Lola","Gato","Angora","Hembra","Recuperándose",java.sql.Date.valueOf("2025-07-17"),java.sql.Date.valueOf("2020-12-05"),4.7f,100009));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2022/11/15/10/19/dog-7593666_640.jpg","Zeus","Perro","Dálmata","Macho","En tratamiento",java.sql.Date.valueOf("2025-06-22"),java.sql.Date.valueOf("2019-01-10"),25.1f,100010));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2017/06/26/12/39/husky-2443664_640.jpg","Simba","Perro","Husky","Macho","Saludable",java.sql.Date.valueOf("2025-12-01"),java.sql.Date.valueOf("2018-07-21"),27.5f,100011));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("alergia al polen"), "Controlada","https://cdn.pixabay.com/photo/2021/11/05/19/30/animal-6771900_640.jpg","Mia","Gato","Bengala","Hembra","Recuperándose",java.sql.Date.valueOf("2025-11-11"),java.sql.Date.valueOf("2019-10-12"),5.4f,100012));
        mascotaRepository.save(new Mascota(  List.of("rabia","leptospirosis"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2018/08/31/12/50/boxer-3644625_640.jpg","Thor","Perro","Boxer","Macho","Control rutinario",java.sql.Date.valueOf("2025-08-08"),java.sql.Date.valueOf("2021-03-18"),29.3f,100013));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2023/09/06/17/03/maine-coon-8237571_640.jpg","Sasha","Gato","Maine Coon","Hembra","Recuperándose",java.sql.Date.valueOf("2025-09-13"),java.sql.Date.valueOf("2018-12-01"),6.9f,100014));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2024/02/17/00/18/cat-8578562_640.jpg","Felix","Gato","Europeo","Macho","En tratamiento",java.sql.Date.valueOf("2025-07-19"),java.sql.Date.valueOf("2020-02-02"),4.3f,100015));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2020/09/02/11/40/shih-tzu-5538105_640.jpg","Bobby","Perro","Shih Tzu","Macho","Saludable",java.sql.Date.valueOf("2025-03-10"),java.sql.Date.valueOf("2019-11-11"),7.6f,100016));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2022/05/03/17/43/animal-7172330_640.jpg","Coco","Perro","Poodle","Macho","Saludable",java.sql.Date.valueOf("2025-05-21"),java.sql.Date.valueOf("2021-01-12"),6.1f,100017));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2020/03/08/18/13/cats-4913336_640.jpg","Chispa","Gato","Abisinio","Hembra","En tratamiento",java.sql.Date.valueOf("2025-06-18"),java.sql.Date.valueOf("2020-09-01"),3.9f,100018));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2017/09/13/16/22/belgian-2746075_1280.jpg","Rex","Perro","Pastor Belga","Macho","Recuperándose",java.sql.Date.valueOf("2025-12-22"),java.sql.Date.valueOf("2017-05-23"),31.4f,100019));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2013/09/03/23/30/animals-178808_640.jpg","Bella","Perro","Chihuahua","Hembra","Control rutinario",java.sql.Date.valueOf("2025-08-09"),java.sql.Date.valueOf("2021-06-06"),2.8f,100020));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2016/11/26/23/47/cat-1861841_640.jpg","Tom","Gato","Siberiano","Macho","Recuperándose",java.sql.Date.valueOf("2025-10-28"),java.sql.Date.valueOf("2018-07-19"),5.7f,100021));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2021/07/06/14/38/border-collie-6391794_640.jpg","Maya","Perro","Border Collie","Hembra","Saludable",java.sql.Date.valueOf("2025-11-15"),java.sql.Date.valueOf("2019-08-30"),19.6f,100022));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2017/03/14/14/49/cat-2143332_640.jpg","Kiara","Gato","British Shorthair","Hembra","En tratamiento",java.sql.Date.valueOf("2025-09-30"),java.sql.Date.valueOf("2020-05-15"),4.8f,100023));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2021/10/14/17/58/dog-6710018_640.jpg","Jack","Perro","Rottweiler","Macho","Recuperándose",java.sql.Date.valueOf("2025-04-12"),java.sql.Date.valueOf("2018-02-22"),33.5f,100024));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2020/03/16/11/46/cat-4936769_640.jpg","Momo","Gato","Ragdoll","Macho","Recuperándose",java.sql.Date.valueOf("2025-07-04"),java.sql.Date.valueOf("2019-09-14"),6.2f,100025));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2016/04/27/17/28/snauzer-1357120_1280.jpg","Nico","Perro","Schnauzer","Macho","Recuperándose",java.sql.Date.valueOf("2025-05-17"),java.sql.Date.valueOf("2020-06-30"),9.4f,100026));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://delosgatosblog.com/wp-content/uploads/Gato-himalayo.webp","Lily","Gato","Himalayo","Hembra","Saludable",java.sql.Date.valueOf("2025-09-21"),java.sql.Date.valueOf("2019-11-07"),4.5f,100027));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2015/11/12/20/32/st-bernard-1040956_640.jpg","Duke","Perro","San Bernardo","Macho","En tratamiento",java.sql.Date.valueOf("2025-12-19"),java.sql.Date.valueOf("2017-10-16"),45.3f,100028));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2018/01/07/15/46/pug-3067597_640.jpg","Tina","Perro","Pug","Hembra","Recuperándose",java.sql.Date.valueOf("2025-02-27"),java.sql.Date.valueOf("2020-04-09"),8.2f,100029));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna"), "Sana","https://estag.fimagenes.com/img/2/2/Z/c/5/2Zc5_900.jpg","Salem","Gato","Bombay","Macho","Control rutinario",java.sql.Date.valueOf("2025-06-01"),java.sql.Date.valueOf("2021-07-07"),4.0f,100030));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Sana","https://cdn.pixabay.com/photo/2016/07/28/20/28/standard-poodle-1549558_640.jpg","Daisy","Perro","Caniche","Hembra","Recuperándose",java.sql.Date.valueOf("2025-08-29"),java.sql.Date.valueOf("2018-05-05"),7.8f,100031));
        mascotaRepository.save(new Mascota(  List.of("parvo"),List.of("ninguna conocida"), "En tratamiento", "https://cdn.pixabay.com/photo/2017/06/29/18/02/dog-2455272_640.jpg","Toby", "Perro", "Beagle", "Macho", "En tratamiento",java.sql.Date.valueOf("2025-07-15"),java.sql.Date.valueOf("2019-05-21"), 12.8f, 123501));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2023/03/09/02/40/siberian-husky-7839113_640.jpg","Luna", "Perro", "Husky", "Hembra", "Recuperándose",java.sql.Date.valueOf("2025-09-10"),java.sql.Date.valueOf("2020-08-30"), 18.3f, 123502));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2022/02/17/04/54/animal-7017939_1280.jpg","Mishu", "Gato", "Persa", "Macho", "Saludable",java.sql.Date.valueOf("2025-06-12"),java.sql.Date.valueOf("2021-01-10"), 6.1f, 123503));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2021/07/25/14/58/siamese-cat-6492109_640.jpg","Nina", "Gato", "Siamés", "Hembra", "Recuperándose",java.sql.Date.valueOf("2025-07-01"),java.sql.Date.valueOf("2022-02-14"), 5.7f, 123504));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna conocida"), "En tratamiento", "https://cdn.pixabay.com/photo/2020/02/29/04/45/german-shepherd-4889095_640.jpg","Zeus", "Perro", "Pastor Alemán", "Macho", "En tratamiento",java.sql.Date.valueOf("2025-08-20"),java.sql.Date.valueOf("2018-09-11"), 35.2f, 123505));
        mascotaRepository.save(new Mascota(  List.of("parvo"),List.of("ninguna conocida"), "Recuperándose", "https://cdn.pixabay.com/photo/2025/07/25/17/29/dog-9735422_640.jpg","Kira", "Perro", "Golden Retriever", "Hembra", "Saludable",java.sql.Date.valueOf("2025-05-23"),java.sql.Date.valueOf("2021-11-19"), 22.9f, 123506));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2017/01/21/18/15/cat-1997911_640.jpg","Simba", "Gato", "Angora", "Macho", "Recuperándose",java.sql.Date.valueOf("2025-06-18"),java.sql.Date.valueOf("2020-03-05"), 7.4f, 123507));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2020/10/05/10/51/cat-5628953_640.jpg","Mimi", "Gato", "Criollo", "Hembra", "Saludable",java.sql.Date.valueOf("2025-09-22"),java.sql.Date.valueOf("2022-04-21"), 4.8f, 123508));
        mascotaRepository.save(new Mascota(  List.of("parvo"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2019/05/07/20/44/dog-4187023_640.jpg","Rocky", "Perro", "Bulldog", "Macho", "Saludable",java.sql.Date.valueOf("2025-07-28"),java.sql.Date.valueOf("2020-12-01"), 20.6f, 123509));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2016/01/11/22/36/dog-1134498_1280.jpg","Coco", "Perro", "Chihuahua", "Macho", "En tratamiento",java.sql.Date.valueOf("2025-08-05"),java.sql.Date.valueOf("2021-07-07"), 3.4f, 123510));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2022/09/06/07/49/cat-7436051_640.jpg","Pelusa", "Gato", "Maine Coon", "Hembra", "Saludable",java.sql.Date.valueOf("2025-06-11"),java.sql.Date.valueOf("2019-10-10"), 8.9f, 123511));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2023/08/26/04/48/siberian-cat-8214143_640.jpg","Lolo", "Gato", "Siberiano", "Macho", "En tratamiento",java.sql.Date.valueOf("2025-07-25"),java.sql.Date.valueOf("2020-06-13"), 9.2f, 123512));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2017/10/02/21/04/doberman-2810203_640.png","Max", "Perro", "Doberman", "Macho", "Saludable",java.sql.Date.valueOf("2025-05-14"),java.sql.Date.valueOf("2019-09-20"), 32.1f, 123513));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2017/07/20/03/50/poodle-2521137_640.jpg","Bella", "Perro", "Poodle", "Hembra", "Recuperándose",java.sql.Date.valueOf("2025-09-01"),java.sql.Date.valueOf("2022-03-12"), 7.3f, 123514));
        mascotaRepository.save(new Mascota(  List.of("parvo"),List.of("ninguna conocida"), "En observación", "https://cdn.pixabay.com/photo/2019/12/24/13/20/dogs-4716740_640.jpg","Chester", "Perro", "Cocker Spaniel", "Macho", "Control rutinario",java.sql.Date.valueOf("2025-07-07"),java.sql.Date.valueOf("2021-08-16"), 14.4f, 123515));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2021/10/27/19/09/cat-6748193_640.jpg","Sasha", "Gato", "Bengalí", "Hembra", "Recuperándose",java.sql.Date.valueOf("2025-06-04"),java.sql.Date.valueOf("2020-09-23"), 5.9f, 123516));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2016/02/10/16/37/cat-1192026_640.jpg","Felix", "Gato", "Criollo", "Macho", "Control rutinario",java.sql.Date.valueOf("2025-08-09"),java.sql.Date.valueOf("2019-11-11"), 4.6f, 123517));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna conocida"), "Recuperándose", "https://cdn.pixabay.com/photo/2016/02/27/20/01/dog-1226205_1280.jpg","Bruno", "Perro", "San Bernardo", "Macho", "Saludable",java.sql.Date.valueOf("2025-09-17"),java.sql.Date.valueOf("2018-04-14"), 45.5f, 123518));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2022/04/15/11/23/dog-7134183_640.jpg","Daisy", "Perro", "Border Collie", "Hembra", "En tratamiento",java.sql.Date.valueOf("2025-07-03"),java.sql.Date.valueOf("2021-10-22"), 19.7f, 123519));
        mascotaRepository.save(new Mascota(  List.of("parvo"),List.of("ninguna conocida"), "En tratamiento", "https://cdn.pixabay.com/photo/2022/10/12/04/06/rottweiler-7515632_640.jpg","Rex", "Perro", "Rottweiler", "Macho", "Saludable",java.sql.Date.valueOf("2025-08-28"),java.sql.Date.valueOf("2019-12-25"), 40.2f, 123520));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2025/06/02/21/36/cat-9637984_640.jpg","Cleo", "Gato", "Esfinge", "Hembra", "Saludable",java.sql.Date.valueOf("2025-06-09"),java.sql.Date.valueOf("2020-02-28"), 3.5f, 123521));
        mascotaRepository.save(new Mascota(  List.of("triple felina"),List.of("ninguna conocida"), "Sana", "https://cdn.pixabay.com/photo/2021/09/19/17/31/animal-6638652_640.jpg","Tom", "Gato", "British Shorthair", "Macho", "En tratamiento",java.sql.Date.valueOf("2025-09-06"),java.sql.Date.valueOf("2021-05-09"), 6.6f, 123522));
        mascotaRepository.save(new Mascota(   List.of("rabia"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2018/08/31/12/50/boxer-3644625_640.jpg", "Rocky", "Perro", "Boxer", "Macho", "En tratamiento", java.sql.Date.valueOf("2025-07-10"), java.sql.Date.valueOf("2019-01-01"), 28.0f, 221001));
        mascotaRepository.save(new Mascota(   List.of("leucemia"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2015/08/09/19/02/cat-882049_640.jpg", "Michi", "Gato", "Siames", "Hembra", "Recuperándose", java.sql.Date.valueOf("2025-06-20"), java.sql.Date.valueOf("2021-03-15"), 5.0f, 221002));
        mascotaRepository.save(new Mascota(   List.of("moquillo"), List.of("ninguna"),   "Recuperada", "https://cdn.pixabay.com/photo/2018/12/23/23/05/dog-3891938_640.jpg", "Luna", "Perro", "Husky", "Hembra", "En tratamiento", java.sql.Date.valueOf("2025-08-15"), java.sql.Date.valueOf("2020-11-05"), 22.4f, 221003));
        mascotaRepository.save(new Mascota(   List.of("parvovirus"), List.of("estrés"),   "Sana",   "https://cdn.pixabay.com/photo/2019/06/11/19/22/schafer-dog-4267734_640.jpg", "Thor", "Perro", "Pastor Alemán", "Macho",   "Saludable", java.sql.Date.valueOf("2025-09-01"), java.sql.Date.valueOf("2018-08-10"), 35.2f, 221004));
        mascotaRepository.save(new Mascota(   List.of("rabia"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2022/04/21/22/50/animal-7148487_640.jpg", "Nieve", "Gato", "Angora", "Hembra", "Saludable", java.sql.Date.valueOf("2025-07-12"), java.sql.Date.valueOf("2022-02-14"), 4.3f, 221005));
        mascotaRepository.save(new Mascota(   List.of("leucemia"), List.of("alergia"),   "Controlada", "https://cdn.pixabay.com/photo/2023/12/22/05/55/dog-8463178_640.jpg", "Boby", "Perro", "Beagle", "Macho","En tratamiento", java.sql.Date.valueOf("2025-08-30"), java.sql.Date.valueOf("2021-06-01"), 14.7f, 221006));
        mascotaRepository.save(new Mascota(   List.of("ninguna"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2018/06/24/10/02/cat-3494225_640.jpg", "Cleo", "Gato", "Persa", "Hembra", "Saludable", java.sql.Date.valueOf("2025-08-01"), java.sql.Date.valueOf("2022-09-10"), 5.5f, 221007));
        mascotaRepository.save(new Mascota(   List.of("rabia"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2017/08/05/21/41/cat-2585836_640.jpg", "Felix", "Gato", "Maine Coon", "Macho", "Saludable", java.sql.Date.valueOf("2025-05-10"), java.sql.Date.valueOf("2019-03-01"), 6.8f, 221008));
        mascotaRepository.save(new Mascota(   List.of("leucemia"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2020/03/16/11/58/dog-4936814_640.jpg", "Kira", "Perro", "Golden Retriever", "Hembra", "Saludable", java.sql.Date.valueOf("2025-07-19"), java.sql.Date.valueOf("2020-02-14"), 32.5f, 221009));
        mascotaRepository.save(new Mascota(   List.of("moquillo"), List.of("ninguna"),   "Recuperado", "https://cdn.pixabay.com/photo/2020/09/02/02/40/cat-5537127_640.jpg", "Tom", "Gato", "Siames", "Macho", "En tratamiento", java.sql.Date.valueOf("2025-08-20"), java.sql.Date.valueOf("2021-04-11"), 5.2f, 221010));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2022/01/02/13/38/animal-6910172_640.jpg","Luna","Perro","Beagle","Hembra","Control rutinario",java.sql.Date.valueOf("2023-07-21"),java.sql.Date.valueOf("2020-02-01"),14.2f,301221));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Protegido","https://cdn.pixabay.com/photo/2022/08/06/12/02/cat-7368478_640.jpg","Milo","Gato","Siberiano","Macho","Saludable",java.sql.Date.valueOf("2022-12-03"),java.sql.Date.valueOf("2019-05-10"),8.7f,301222));
        mascotaRepository.save(new Mascota(  List.of("leucemia"),List.of("ninguna"), "En tratamiento","https://cdn.pixabay.com/photo/2021/12/17/13/35/cat-6876586_640.jpg","Nala","Gato","Angora","Hembra","Saludable",java.sql.Date.valueOf("2024-06-15"),java.sql.Date.valueOf("2018-11-12"),7.3f,301223));
        mascotaRepository.save(new Mascota(  List.of("parvovirus"),List.of("ninguna"), "Vacunado","https://cdn.pixabay.com/photo/2013/11/28/12/14/dog-220455_640.jpg","Simba","Perro","Golden Retriever","Macho","En tratamiento",java.sql.Date.valueOf("2025-01-01"),java.sql.Date.valueOf("2019-01-01"),30.0f,301224));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2024/01/07/08/06/french-bulldog-8492504_640.jpg","Bella","Perro","Bulldog","Hembra","Saludable",java.sql.Date.valueOf("2024-09-15"),java.sql.Date.valueOf("2020-07-07"),22.1f,301225));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Protegido","https://cdn.pixabay.com/photo/2019/01/25/21/17/dog-3955338_640.jpg","Rocky","Perro","Pastor Alemán","Macho","Recuperándose",java.sql.Date.valueOf("2023-03-17"),java.sql.Date.valueOf("2018-08-08"),34.5f,301226));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2020/09/02/02/42/cat-5537135_640.jpg","Mimi","Gato","Siames","Hembra","Recuperándose",java.sql.Date.valueOf("2024-11-01"),java.sql.Date.valueOf("2020-02-14"),5.9f,301227));
        mascotaRepository.save(new Mascota(  List.of("leptospirosis"),List.of("ninguna"), "En control","https://cdn.pixabay.com/photo/2011/05/16/21/33/siberian-husky-7379_640.jpg","Zeus","Perro","Husky","Macho","Recuperándose",java.sql.Date.valueOf("2025-02-12"),java.sql.Date.valueOf("2020-03-03"),28.7f,301228));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2017/01/21/08/19/cat-1996569_640.jpg","Kira","Gato","Persa","Hembra","Saludable",java.sql.Date.valueOf("2023-05-10"),java.sql.Date.valueOf("2019-06-01"),6.0f,301229));
        mascotaRepository.save(new Mascota(  List.of("parvovirus"),List.of("ninguna"), "Vacunado","https://cdn.pixabay.com/photo/2019/01/20/17/28/dog-3944250_640.jpg","Thor","Perro","Doberman","Macho","Saludable",java.sql.Date.valueOf("2024-08-18"),java.sql.Date.valueOf("2018-12-25"),32.4f,301230));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Protegida","https://cdn.pixabay.com/photo/2019/02/01/20/11/dog-3969731_640.jpg","Lola","Perro","Cocker Spaniel","Hembra","En tratamiento",java.sql.Date.valueOf("2023-04-14"),java.sql.Date.valueOf("2019-09-09"),12.8f,301231));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Sano","https://cdn.pixabay.com/photo/2018/04/27/16/49/dog-3355192_640.jpg","Max","Perro","Boxer","Macho","En tratamiento",java.sql.Date.valueOf("2025-07-11"),java.sql.Date.valueOf("2018-05-11"),29.9f,301232));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2014/07/13/16/33/maine-coon-cat-392573_640.jpg","Chloe","Gato","Maine Coon","Hembra","Saludable",java.sql.Date.valueOf("2022-11-30"),java.sql.Date.valueOf("2019-07-20"),9.1f,301233));
        mascotaRepository.save(new Mascota(  List.of("leucemia"),List.of("ninguna"), "En control","https://cdn.pixabay.com/photo/2023/12/15/21/47/cat-8451432_640.jpg","Tom","Gato","Europeo","Macho","En tratamiento",java.sql.Date.valueOf("2023-06-06"),java.sql.Date.valueOf("2020-09-15"),7.0f,301234));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Protegido","https://cdn.pixabay.com/photo/2018/08/20/14/08/dog-3619020_640.jpg","Sasha","Perro","Pomerania","Hembra","Saludable",java.sql.Date.valueOf("2025-03-01"),java.sql.Date.valueOf("2020-11-01"),5.1f,301235));
        mascotaRepository.save(new Mascota(  List.of("parvovirus"),List.of("ninguna"), "Sano","https://cdn.pixabay.com/photo/2013/07/01/16/19/dog-142689_640.jpg","Bobby","Perro","San Bernardo","Macho","Recuperándose",java.sql.Date.valueOf("2024-10-19"),java.sql.Date.valueOf("2019-12-05"),40.0f,301236));
        mascotaRepository.save(new Mascota(  List.of("moquillo"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2018/08/20/21/57/dog-3620113_640.jpg","Nina","Gato","Bengala","Hembra","Saludable",java.sql.Date.valueOf("2022-08-25"),java.sql.Date.valueOf("2018-10-30"),6.3f,301237));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Protegido","https://cdn.pixabay.com/photo/2023/11/30/07/04/shetland-sheepdog-8420917_640.jpg","Rex","Perro","Pastor Belga","Macho","Saludable",java.sql.Date.valueOf("2023-09-09"),java.sql.Date.valueOf("2019-04-14"),27.5f,301238));
        mascotaRepository.save(new Mascota(  List.of("leucemia"),List.of("ninguna"), "En tratamiento","https://cdn.pixabay.com/photo/2015/01/31/12/36/cat-618470_640.jpg","Coco","Gato","Criollo","Macho","Control rutinario",java.sql.Date.valueOf("2024-01-17"),java.sql.Date.valueOf("2020-06-18"),8.0f,301239));
        mascotaRepository.save(new Mascota(  List.of("rabia"),List.of("ninguna"), "Vacunada","https://cdn.pixabay.com/photo/2015/11/07/12/39/chihuahua-1031827_640.jpg","Daisy","Perro","Chihuahua","Hembra","Control rutinario",java.sql.Date.valueOf("2025-05-27"),java.sql.Date.valueOf("2021-03-03"),3.5f,301240));
        
        mascotaRepository.save(new Mascota(   List.of("moquillo"), List.of("ninguna conocida"),   "Sana", "https://cdn.pixabay.com/photo/2020/07/11/03/41/beagle-5392662_640.jpg", "Rocky", "Perro", "Beagle", "Macho", "Saludable", java.sql.Date.valueOf("2024-05-15"), java.sql.Date.valueOf("2019-08-20"), 12.3f, 789012));
        mascotaRepository.save(new Mascota(   List.of("rabia"), List.of("alergia al polen"),   "Controlada", "https://cdn.pixabay.com/photo/2017/04/04/05/55/thai-cat-2200469_640.jpg", "Misha", "Gato", "Siames", "Hembra", "Saludable", java.sql.Date.valueOf("2023-11-10"), java.sql.Date.valueOf("2018-04-12"), 4.7f, 345678));
        mascotaRepository.save(new Mascota(   List.of("parvovirus"), List.of("ninguna conocida"),   "Recuperada", "https://cdn.pixabay.com/photo/2016/10/26/14/47/siberian-husky-1771667_640.jpg", "Thor", "Perro", "Husky", "Macho", "En tratamiento", java.sql.Date.valueOf("2025-02-02"), java.sql.Date.valueOf("2020-07-01"), 22.1f, 901234));
        mascotaRepository.save(new Mascota(   List.of("leucemia"), List.of("ninguna conocida"),   "En tratamiento", "https://cdn.pixabay.com/photo/2015/12/04/21/53/cat-1077516_640.jpg", "Luna", "Gato", "Persa", "Hembra", "Control rutinario", java.sql.Date.valueOf("2025-06-21"), java.sql.Date.valueOf("2017-02-14"), 3.9f, 567890));
        mascotaRepository.save(new Mascota(   List.of("rabia", "moquillo"), List.of("ninguna conocida"),   "Sana", "https://cdn.pixabay.com/photo/2017/09/30/13/19/dog-2802074_640.jpg", "Max", "Perro", "Pastor Alemán", "Macho", "Saludable", java.sql.Date.valueOf("2025-07-12"), java.sql.Date.valueOf("2021-05-30"), 28.4f, 112233));
        mascotaRepository.save(new Mascota(   List.of("ninguna"), List.of("ninguna"),   "Sana", "https://cdn.pixabay.com/photo/2022/09/12/22/11/dog-7450573_640.jpg", "Kira", "Perro", "Golden Retriever", "Hembra", "Saludable", java.sql.Date.valueOf("2025-01-18"), java.sql.Date.valueOf("2020-09-15"), 26.0f, 445566));
        mascotaRepository.save(new Mascota(   List.of("leucemia"), List.of("asma felina"),   "Delicada", "https://cdn.pixabay.com/photo/2015/03/27/13/10/cat-694718_640.jpg", "Nube", "Gato", "Maine Coon", "Hembra", "Saludable", java.sql.Date.valueOf("2024-12-05"), java.sql.Date.valueOf("2019-03-23"), 6.8f, 778899));

        //Asociacion Mascota Usuario
        for(Long i=1L; i<= 101; i++) {
            Usuario usuario = new Usuario();
            if (i<=50) {
                usuario = usuarioRepository.findById(i).get();
            }else {
                if (i==101) {
                    usuario = usuarioRepository.findById(i-51).get();
                }else{
                    usuario = usuarioRepository.findById(i-50).get();
                }
                
            }
            Mascota mascota = mascotaRepository.findById(i).get();
            mascota.setDueño(usuario);
            mascotaRepository.save(mascota);
            

        }
        
        veterinarioRepository.save(new Veterinario("Alberto","veterinario@gmail.com","1234",1,"Ortopedia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Beatriz","vet@gmail.com","1234",2,"Cardiologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Carlos","vete@gmail.com","1234",3,"Dermatologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Diana","veterinaria@gmail.com","1234",4,"Pediatria","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Elena","vett@gmail.com","1234",5,"Traumatologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Fernando","veter@gmail.com","1234",6,"Neurologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Gabriela","veteri@gmail.com","1234",7,"Oftalmologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Hector","veterin@gmail.com","1234",8,"Urologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Isabel","veterina@gmail.com","1234",9,"Ginecologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
        veterinarioRepository.save(new Veterinario("Javier","veterinar@gmail.com","1234",10,"Oncologia","https://cdn.pixabay.com/photo/2024/01/20/06/06/ai-generated-8520391_640.png"));
    
        tratamientoRepository.save(new Tratamiento("Quimioterapia"));
        tratamientoRepository.save(new Tratamiento("Fisioterapia"));
        tratamientoRepository.save(new Tratamiento("Infeccion"));
        tratamientoRepository.save(new Tratamiento("Vacunacion"));
        tratamientoRepository.save(new Tratamiento("Desparasitacion"));
        tratamientoRepository.save(new Tratamiento("Cuidado Oidos"));
        tratamientoRepository.save(new Tratamiento("Cuidado dental"));
        tratamientoRepository.save(new Tratamiento("Castracion"));
        tratamientoRepository.save(new Tratamiento("Virus"));
        tratamientoRepository.save(new Tratamiento("Cuidado Ojos"));

        medicamentoRepository.save(new Medicamento("Doxorrubicina", 120000,70));
        medicamentoRepository.save(new Medicamento("DM-Gel", 90000,60));
        medicamentoRepository.save(new Medicamento("Amoxicilina", 150000,50));
        medicamentoRepository.save(new Medicamento("Nytax", 84000,20));
        medicamentoRepository.save(new Medicamento("Vacuna Antirabica", 60000,80));
        medicamentoRepository.save(new Medicamento("Itraclonazol", 50000,200));
        medicamentoRepository.save(new Medicamento("Clordent", 300000,30));
        medicamentoRepository.save(new Medicamento("propofol", 250000,40));
        medicamentoRepository.save(new Medicamento("Rimadyl", 100000,100));
        medicamentoRepository.save(new Medicamento("Ciclosporina", 80000,150));

        
        for(Long i=1L; i<=10; i++) {
            Tratamiento tratamiento = tratamientoRepository.findById(i).get();
            Veterinario veterinario = veterinarioRepository.findById(i).get();
            Mascota mascota = mascotaRepository.findById(i).get();
            Medicamento medicamento = medicamentoRepository.findById(i).get();
            tratamiento.setMedicamentos(new ArrayList<>(List.of(medicamento)));
            tratamiento.setMascota(mascota);
            tratamiento.setVeterinario(veterinario);
            tratamientoRepository.save(tratamiento);
        }
    }
    
}
