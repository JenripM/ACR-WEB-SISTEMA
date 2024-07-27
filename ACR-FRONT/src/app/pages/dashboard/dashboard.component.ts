import {
  Component,
  ElementRef,
  TemplateRef,
  ViewChild,
  inject,
} from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { NgxLoadingModule, ngxLoadingAnimationTypes } from "ngx-loading";
import { trigger, style, transition, animate } from "@angular/animations";
import { DataService } from "../../data.service";
import { CommonModule } from "@angular/common";
import { ConvertTextToHtmlPipe } from "../../convert-text-to-html.pipe";
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
} from "@angular/forms";
import { GeminiConfig } from "../../chat-form";
import { API_KEY_CONF } from "../../../config";
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { MatIconModule } from "@angular/material/icon";
import { MatSelectModule } from "@angular/material/select";
@Component({
  selector: "app-dashboard",
  standalone: true,
  imports: [
    RouterOutlet,
    CommonModule,
    NgxLoadingModule,
    ConvertTextToHtmlPipe,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    MatIconModule,
    MatSelectModule
  ],
  templateUrl: "./dashboard.component.html",
  styleUrl: "./dashboard.component.scss",
  animations: [
    trigger("typeWritterEffect", [
      transition(":enter", [
        style({ opacity: 0 }),
        animate("2s", style({ opacity: 1 })),
      ]),
    ]),
  ],
})
export class AppDashboardComponent {
  @ViewChild("messagesContainer") private messagesContainer!: ElementRef;
  private dataService = inject(DataService);
  public messagesHistory: { role: string; parts: string }[] = [];
  public userMessage!: string | null;
  public loading = false;
  public loadingTemplate!: TemplateRef<any>;
  public ngxLoadingAnimationTypes = ngxLoadingAnimationTypes;
  public loadingConfig = {
    animationType: ngxLoadingAnimationTypes.circleSwish,
    primaryColour: "#ffffff",
    secondaryColour: "#ccc",
    tertiaryColour: "#dd0031",
    backdropBorderRadius: "3px",
  };
  public gQuestions = [
    
  ];
  public bQuestions = [
   
  ];

  public characterSelection = [
    {
      id: 0,
      value: "Rosales y Asociados",
    },
    {
      id: 1,
      value: "Gemini",
    },
  ];

  temperatureOptions = [
    { value: 0.2, label: "Low Creativity" },
    { value: 0.5, label: "Moderate Creativity" },
    { value: 0.9, label: "High Creativity" },
  ];

  modelOptions = [
    { label: "Gemini v1.0.0-Pro (Basic)", value: "gemini-1.0-pro" },
    { label: "Gemini v1.0.0-Pro-001 (Updated)", value: "gemini-1.0-pro-001" },
    {
      label: "Gemini v1.5 (Experimental)",
      value: "gemini-1.5-pro",
      disabled: true,
    },
  ];

  chatForm = new FormGroup({
    apiKey: new FormControl(API_KEY_CONF || ""),
    temperature: new FormControl(this.temperatureOptions[2].value),
    bot: new FormControl(this.characterSelection[0]),
    model: new FormControl(this.modelOptions[0].value),
  });

  sendMessage(message: string) {
    if (!message || this.loading) return;
    setTimeout(() => this.scrollToBottom(), 0);
    this.loading = true;
    this.messagesHistory.push(
      {
        role: "user",
        parts: message,
      },
      {
        role: "model",
        parts: "",
      }
    );
    this.dataService
      .generateContentWithGeminiPro(
        message,
        this.messagesHistory,
        this.chatForm.value as GeminiConfig
      )
      .subscribe({
        next: (res: any) => {
          this.loading = false;
          this.userMessage = null;
          this.messagesHistory = this.messagesHistory.slice(0, -2);
          this.messagesHistory.push(
            {
              role: "user",
              parts: message,
            },
            {
              role: "model",
              parts: res,
            }
          );
          setTimeout(() => this.scrollToBottom(), 0);
        },
        error: (error) => {
          this.loading = false;
          console.error("Error generating content:", error);
          this.messagesHistory.push({
            role: "model",
            parts: "Lo Siento, Intenta nuevamente.",
          });
          setTimeout(() => this.scrollToBottom(), 0);
        },
      });
  }

  scrollToBottom(): void {
    try {
      this.messagesContainer.nativeElement.scrollTop =
        this.messagesContainer.nativeElement.scrollHeight;
    } catch (err) {}
  }

  ngAfterViewInit() {
    this.scrollToBottom();
  }
}
