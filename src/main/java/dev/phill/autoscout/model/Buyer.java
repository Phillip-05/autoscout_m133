package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Buyer {


    private String BuyerUUID;

    private Integer jahrgang;

    @JsonIgnore
    private Watchlist watchlist;

    /**
     * gets Watchlist by its uuid
     * @return Watchlist
     */
    public String getWatchlistUUID() {
        return getWatchlist().getWatchlistUUID();
    }

    /**
     * sets Watchlist by its uuid
     * @param: watchlistUUID
     */
    public void setWatchlistUUID(String watchlistUUID) {
        setWatchlist(new Watchlist());
        Watchlist watchlist = DataHandler.readWatchlistByUUID(watchlistUUID);
        getWatchlist().setWatchlistUUID(watchlistUUID);
        getWatchlist().setWatchlistUUID(watchlist.getWatchlistUUID());

    }

    /**
     * Buyer constructor
     * @param: buyeruuid
     * @param: jahrgang
     * @param: merkliste
     */
    public Buyer(String buyerUUID, Integer jahrgang, Watchlist watchlist) {
        BuyerUUID = buyerUUID;
        this.jahrgang = jahrgang;

        this.watchlist = watchlist;
    }
}
