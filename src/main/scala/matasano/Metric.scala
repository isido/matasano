package matasano

import math.pow

trait Metric[T] {
  // http://docs.opencv.org/doc/tutorials/imgproc/histograms/histogram_comparison/histogram_comparison.html
  def distance(a: Map[T, Double], b: Map[T, Double]): Double
}

trait Dummy[T] extends Metric[T] {
  def distance(a: Map[T, Double], b: Map[T, Double]): Double = 0
}

trait ChiSquare[T] extends Metric[T] {
  def distance(a: Map[T, Double], b: Map[T, Double]): Double =
    a.foldLeft(0.0){ case (acc, (ch, v)) => acc + (pow(v - b.getOrElse(ch, 99.0), 2) / v) } // find mathematically valid value here
}

