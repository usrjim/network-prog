
(import '[java.net Socket]
        '[java.io BufferedReader InputStreamReader])

(def host "time-a.nist.gov")
(def port 13)

(let [socket (Socket. host port)
      in (-> socket
             .getInputStream
             InputStreamReader.
             BufferedReader.)]
  (loop []
    (when-let [n (.readLine in)]
      (println n)
      (recur)))
  (.close in)
  (.close socket))
