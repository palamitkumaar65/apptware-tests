package com.apptware.interview.stream.impl;

import com.apptware.interview.stream.DataReader;
import com.apptware.interview.stream.PaginationService;
import jakarta.annotation.Nonnull;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class DataReaderImpl implements DataReader {

  @Autowired private PaginationService paginationService;

  @Override
  public Stream<String> fetchLimitadData(int limit) {
    return fetchPaginatedDataAsStream().limit(limit);
  }

  @Override
  public Stream<String> fetchFullData() {
    return fetchPaginatedDataAsStream();
  }

  /**
   * This method is where the candidate should add the implementation. Logs have been added to track
   * the data fetching behavior. Do not modify any other areas of the code.
   */
  private @Nonnull Stream<String> fetchPaginatedDataAsStream() {
    log.info("Fetching paginated data as stream.");

    // Placeholder for paginated data fetching logic
    // The candidate will add the actual implementation here

//    Stream<String> dataStream =
//        Stream.empty(); // Temporary, will be replaced by the actual data stream

    int pageSize = 1000; // Define the page size for each pagination call
    int totalPages = (int) Math.ceil((double) PaginationService.FULL_DATA_SIZE / pageSize);

    Stream<String> dataStream = Stream.iterate(1, page -> page <= totalPages, page -> page + 1)
            .flatMap(page -> paginationService.getPaginatedData(page, pageSize).stream());

    return dataStream.peek(item -> log.info("Fetched Item: {}", item));
  }
}
