(defproject todomvc-re-frame "0.10.9"
  :dependencies [[org.clojure/clojure        "1.10.1"]
                 [org.clojure/clojurescript  "1.10.520"
                  :exclusions [com.google.javascript/closure-compiler-unshaded
                               org.clojure/google-closure-library]]
                 [thheller/shadow-cljs "2.8.67"]
                 [reagent "0.8.1"]
                 [re-frame "0.10.9"]
                 [day8.re-frame/tracing "0.5.3"]
                 [day8.re-frame/re-frame-10x "0.4.4"]
                 [secretary "1.2.3"]]

  :plugins [[lein-shadow "0.1.6"]]

  :profiles {:dev  {:dependencies [[binaryage/devtools "0.9.10"]]}}

  :source-paths ["src" "../../src" "../../gen-src"]

  :clean-targets ^{:protect false} [:target-path
                                    "resources/public/js"]

  :shadow-cljs {:nrepl  {:port 8777}

                :builds {:app {:target     :browser
                               :output-dir "resources/public/js"
                                 :modules    {:todomvc {:init-fn  todomvc.core/main
                                                        :preloads [day8.re-frame-10x.preload]}}
                               :dev        {:compiler-options {:closure-defines {re-frame.trace.trace-enabled?        true
                                                                                 day8.re-frame-10x.debug?             true
                                                                                 day8.re-frame.tracing.trace-enabled? true}
                                                               :external-config  {:devtools/config {:features-to-install [:formatters :hints]}}}}
                               :devtools   {:http-root "resources/public"
                                            :http-port 8280}}}}

  :aliases {"dev-auto" ["with-profile" "dev" "shadow" "watch" "app"]})
