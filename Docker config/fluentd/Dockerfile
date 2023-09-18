FROM fluent/fluentd:v1.12-debian

USER root
RUN gem install elasticsearch -v 7.13
RUN gem install fluent-plugin-elasticsearch
RUN gem install fluent-plugin-kafka
RUN gem install fluent-plugin-record-reformer
USER fluent

