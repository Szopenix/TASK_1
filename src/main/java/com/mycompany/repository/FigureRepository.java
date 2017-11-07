package com.mycompany.repository;

import com.mycompany.domain.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FigureRepository extends JpaRepository<Figure, Integer> {
}
