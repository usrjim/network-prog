
(import '[java.net ServerSocket Socket]
        '[java.io BufferedReader InputStreamReader PrintWriter])

(def port 4444)
(def server (ServerSocket. port))

(println "listening for connection on port " port)

(while true
  (let [client (.accept server)
        in (-> client
               .getInputStream
               InputStreamReader.
               BufferedReader.)
        out (-> client
                .getOutputStream
                PrintWriter.)]
    (println "received connection from"
             (str (.getRemoteSocketAddress client)))

    (.println out "My echo server 1.0")
    (.flush out)

    (loop []
      (when-let [line (.readLine in)]
        (.println out (str "Got:" line))
        (.flush out)
        (recur)))
    
    (.close in)
    (.close out)
    (.close client)
    (println "connection closed")))
