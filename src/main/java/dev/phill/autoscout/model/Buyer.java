package dev.phill.autoscout.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.phill.autoscout.data.DataHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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
     * @param: watchlisUUID
     */
    public void setWatchlistUUID(String watchlistUUID) {
        setWatchlist(new Watchlist());
        Watchlist watchlist = DataHandler.getInstance().readWatchlistByUUID(watchlistUUID);
        getWatchlist().setWatchlistUUID(watchlistUUID);
        getWatchlist().setWatchlistUUID(watchlist.getWatchlistUUID());

    }

    /**
     * Buyer constructor
     * @param: Buyeruuid
     * @param: jahrgang
     * @param: merkliste
     */
    public Buyer(String buyerUUID, Integer jahrgang, Watchlist watchlist) {
        BuyerUUID = buyerUUID;
        this.jahrgang = jahrgang;

        this.watchlist = watchlist;
    }
}
