import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home implements OnInit {
  stats = [
    { 
      id: 1, 
      targetValue: 99.9, 
      currentValue: 0, 
      suffix: '%', 
      label: 'Inventory Accuracy', 
      animated: false,
      color: '#dc2626' // Rojo inicial
    },
    { 
      id: 2, 
      targetValue: 50, 
      currentValue: 0, 
      suffix: '%', 
      label: 'Loss Reduction', 
      animated: false,
      color: '#dc2626'
    },
    { 
      id: 3, 
      targetValue: 24, 
      currentValue: 0, 
      suffix: '/7', 
      label: 'Real Time Control', 
      animated: false,
      color: '#dc2626'
    },
    { 
      id: 4, 
      targetValue: 100, 
      currentValue: 0, 
      suffix: '+', 
      label: 'Companies Trust', 
      animated: false,
      color: '#dc2626'
    }
  ];

  ngOnInit() {
    this.setupScrollAnimation();
  }

  private setupScrollAnimation() {
    setTimeout(() => {
      const observer = new IntersectionObserver((entries) => {
        entries.forEach(entry => {
          if (entry.isIntersecting) {
            this.animateStats();
            observer.unobserve(entry.target);
          }
        });
      }, { 
        threshold: 0.2,
        rootMargin: '0px 0px -100px 0px'
      });

      const heroSection = document.querySelector('.hero');
      if (heroSection) {
        observer.observe(heroSection);
      }
    }, 300);
  }

  private animateStats() {
    this.stats.forEach((stat, index) => {
      setTimeout(() => {
        stat.animated = true;
        this.animateNumberWithColor(stat);
      }, index * 400); // Más delay entre cada estadística
    });
  }

  private animateNumberWithColor(stat: any) {
    const duration = 1500; // 1.5 segundos - mucho más lento
    const startTime = Date.now();
    const startValue = 0;
    const endValue = stat.targetValue;

    const animate = () => {
      const currentTime = Date.now();
      const elapsed = currentTime - startTime;
      const progress = Math.min(elapsed / duration, 1);
      
      // Valor actual
      if (stat.suffix === '%') {
        stat.currentValue = parseFloat((startValue + (endValue - startValue) * progress).toFixed(1));
      } else {
        stat.currentValue = Math.floor(startValue + (endValue - startValue) * progress);
      }

      // Cambio de color progresivo basado en el progreso
      stat.color = this.getColorByProgress(progress);

      if (progress < 1) {
        requestAnimationFrame(animate);
      } else {
        stat.currentValue = endValue;
        stat.color = '#0078ff'; // Azul final
        this.addCompletionEffect(stat.id);
      }
    };

    animate();
  }

  private getColorByProgress(progress: number): string {
    // Rojo (bajo) → Amarillo (medio) → Azul (alto)
    if (progress < 0.33) {
      // Rojo a Amarillo (0% a 33%)
      const subProgress = progress / 0.33;
      return this.interpolateColor('#ff003c', '#ff9100', subProgress);
    } else if (progress < 0.66) {
      // Amarillo a Azul claro (33% a 66%)
      const subProgress = (progress - 0.33) / 0.33;
      return this.interpolateColor('#ff9100', '#60a5fa', subProgress);
    } else {
      // Azul claro a Azul final (66% a 100%)
      const subProgress = (progress - 0.66) / 0.34;
      return this.interpolateColor('#60a5fa', '#0078ff', subProgress);
    }
  }

  private interpolateColor(color1: string, color2: string, factor: number): string {
    const hex = (color: string) => parseInt(color.slice(1), 16);
    const r1 = (hex(color1) >> 16) & 255;
    const g1 = (hex(color1) >> 8) & 255;
    const b1 = hex(color1) & 255;
    
    const r2 = (hex(color2) >> 16) & 255;
    const g2 = (hex(color2) >> 8) & 255;
    const b2 = hex(color2) & 255;
    
    const r = Math.round(r1 + (r2 - r1) * factor);
    const g = Math.round(g1 + (g2 - g1) * factor);
    const b = Math.round(b1 + (b2 - b1) * factor);
    
    return `#${((1 << 24) + (r << 16) + (g << 8) + b).toString(16).slice(1)}`;
  }

  private addCompletionEffect(statId: number) {
    const statElement = document.querySelector(`.stat:nth-child(${statId})`);
    const numberElement = document.querySelector(`.stat:nth-child(${statId}) .number`);
    
    if (statElement && numberElement) {
      statElement.classList.add('completed');
      numberElement.classList.add('pulse');
      
      setTimeout(() => {
        numberElement.classList.remove('pulse');
      }, 1000);
    }
  }
}