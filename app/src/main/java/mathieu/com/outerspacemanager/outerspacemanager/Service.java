package mathieu.com.outerspacemanager.outerspacemanager;

import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Building;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Buildings;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Fleet;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Galaxies;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.Ship;
import mathieu.com.outerspacemanager.outerspacemanager.classObjet.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Piou on 06/03/2017.
 */

public interface Service {
    //Inscription
    @POST("/api/v1/auth/create")
    Call<User> createUserAccount(@Body User user);

    //Connection
    @POST("api/v1/auth/login")
    Call<User> toConnect(@Body User user);

    //Recupère données de l'utilisateur
    @GET("/api/v1/users/get")
    Call<User> getUserAccount(@Header("x-access-token") String token);

    //Recupère tous les utilisateurs -> Galaxies
    @GET("/api/v1/users/0/10")
    Call<Galaxies> getAllUsers(@Header("x-access-token") String token);

    //Recupère les buildings de l'user
    @GET("/api/v1/buildings/list")
    Call<Buildings> getBuildings(@Header("x-access-token") String token);

    //Construction des bâtiments
    @POST("api/v1/buildings/create/{buildingId}")
    Call<Building> constructBuilding(@Header("x-access-token") String token, @Path("buildingId") int buildingId);

    //Recupère la flotte de l'user
    @GET("/api/v1/fleet/list")
    Call<Fleet> getFleet(@Header("x-access-token") String token);

}
