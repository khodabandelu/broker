FROM cassandra:latest

ENV CASSANDRA_KEYSPACE brokerAccount
ENV TABLE test_table

COPY entrypoint-wrap.sh /entrypoint-wrap.sh
ENTRYPOINT ["entrypoint-wrap.sh"]
CMD ["cassandra", "-f"]