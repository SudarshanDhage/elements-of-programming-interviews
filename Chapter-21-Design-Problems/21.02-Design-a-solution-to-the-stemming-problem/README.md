# 21.02 - Design a solution to the stemming problem

## 📌 System Design Overview
System Design Problem **21.02** from *Elements of Programming Interviews in Java* (Chapter 21: Design Problems).

---

### 🎯 Requirements & Goals

#### Functional Requirements
- Core user capabilities and primary features.

#### Non-Functional Requirements
- **Scalability:** Horizontal scaling for high read/write volume.
- **Availability vs Consistency (CAP):** Target SLA (e.g., 99.99%), eventual vs strong consistency.
- **Latency & Throughput:** Response time targets (p95 / p99).

---

### 📐 Capacity & Scale Estimation
- **Traffic / Throughput (QPS):** Read vs Write ratio, peak loads.
- **Storage & Data Growth:** Per-record size, daily ingestion, retention period.
- **Network Bandwidth:** Inbound / outbound throughput.

---

### 🏗️ High-Level Architecture

```
[Clients] ---> [Load Balancer / API Gateway] ---> [Application Services]
                                                        |
                                          +-------------+-------------+
                                          |                           |
                                      [Cache]                     [Storage]
```

---

### 💾 Data Model & Storage Layer
- **Entities & Schemas:**
- **Storage Technology:** (Relational DB, Document Store, Key-Value, Inverted Index, Object Store)
- **Data Partitioning & Sharding:** Partition keys, replication strategy.

---

### ⚙️ Deep Dive & Component Design
- **Core Algorithms / Workflow:**
- **Throttling & Rate Limiting:**
- **Caching Strategy:** Cache aside / write-through / eviction policy.
- **Fault Tolerance & Reliability:** Handling node failures, replication lag.

---

### ⚖️ Bottlenecks & Trade-offs
- Key trade-offs and alternatives evaluated.
