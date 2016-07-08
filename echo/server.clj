
(import '[java.net ServerSocket]
        '[java.io InputStreamReader PrintWriter BufferedReader])

(def port 4444)
(def server (ServerSocket. port))

(println "server listening on port" port)

(while true
  (let [client (.accept server)
        in (-> client
               .getInputStream
               InputStreamReader.
               BufferedReader.)
        out (-> client
                .getOutputStream
                PrintWriter.)]
    (println "accepted from client" (.getHostName (.getInetAddress client)))
    (loop []
      (when-let [n (.readLine in)]
        (println "received" n)
        (binding [*out* out]
          (println "sent" n))
        (recur)))
    (.close client)))

