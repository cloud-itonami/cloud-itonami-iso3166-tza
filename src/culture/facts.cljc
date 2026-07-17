(ns culture.facts
  "Country-level regional-culture catalog for Tanzania (TZA) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"TZA"
   [{:culture/id "tza.dish.ugali"
     :culture/name "Ugali"
     :culture/country "TZA"
     :culture/kind :dish
     :culture/summary "Cornmeal porridge cooked in boiling water or milk to a stiff, dough-like consistency; the most common staple starch of the African Great Lakes region, shared across Tanzania, Kenya, Uganda and South Sudan, and added to the UNESCO Representative List of the Intangible Cultural Heritage of Humanity in 2017."
     :culture/url "https://en.wikipedia.org/wiki/Ugali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.dish.mishkaki"
     :culture/name "Mishkaki"
     :culture/country "TZA"
     :culture/kind :dish
     :culture/summary "Tanzanian skewers of marinated grilled beef, per the Wikipedia article on Tanzanian cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Tanzanian_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.dish.mkate-wa-kumimina"
     :culture/name "Mkate wa kumimina"
     :culture/country "TZA"
     :culture/kind :dish
     :culture/summary "Zanzibari rice bread, listed among snack foods in the Wikipedia article on Tanzanian cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Tanzanian_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.product.tanzanite"
     :culture/name "Tanzanite"
     :culture/country "TZA"
     :culture/kind :product
     :culture/summary "Blue and violet variety of the mineral zoisite, exclusively found in Tanzania; named by Tiffany & Co. after the country following its discovery in 1967, making Tanzania the sole source of this gemstone."
     :culture/url "https://en.wikipedia.org/wiki/Tanzanite"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.craft.tingatinga-painting"
     :culture/name "Tingatinga painting"
     :culture/country "TZA"
     :culture/kind :craft
     :culture/summary "East African painting style, one of the most widely represented forms of tourist-oriented paintings in Tanzania and Kenya, named after Tanzanian painter Edward Saidi Tingatinga and traditionally made with bicycle paint on masonite."
     :culture/url "https://en.wikipedia.org/wiki/Tingatinga_(painting)"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.festival.sauti-za-busara"
     :culture/name "Sauti za Busara"
     :culture/country "TZA"
     :culture/kind :festival
     :culture/summary "African music festival held every year in February in Zanzibar, Tanzania, centered at the Old Fort with events throughout Stone Town; one of East Africa's largest music festivals, showcasing genres from traditional Taarab to contemporary Tanzanian pop and hip-hop."
     :culture/url "https://en.wikipedia.org/wiki/Sauti_za_Busara"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.heritage.stone-town-zanzibar"
     :culture/name "Stone Town"
     :culture/country "TZA"
     :culture/kind :heritage
     :culture/summary "Historic old quarter of Zanzibar City, designated a UNESCO World Heritage Site in 2000, renowned for architecture blending Arab, Persian, Indian, and European influences and its role as a center of the 19th-century spice and slave trades."
     :culture/url "https://en.wikipedia.org/wiki/Stone_Town"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "tza.heritage.ngorongoro-conservation-area"
     :culture/name "Ngorongoro Conservation Area"
     :culture/country "TZA"
     :culture/kind :heritage
     :culture/summary "Protected area in Ngorongoro District, Tanzania, designated a UNESCO World Heritage Site in 1979 for its natural significance and later recognized for its cultural and anthropological importance."
     :culture/url "https://en.wikipedia.org/wiki/Ngorongoro_Conservation_Area"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-tza culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "TZA"))
                 " TZA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
